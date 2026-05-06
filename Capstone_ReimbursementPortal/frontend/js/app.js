const API_BASE = "http://localhost:8080/api";

let currentUser = null;
let dashboardClaims = [];
let employeePage = 0;
let reviewPage = 0;
const pageSize = 5;

function $(id) {
  return document.getElementById(id);
}

function toast(message, type = "success") {
  const box = document.createElement("div");
  box.className = `toast ${type}`;
  box.innerText = message;
  $("toastContainer").appendChild(box);
  setTimeout(() => box.remove(), 3000);
}

function getUser() {
  const data = localStorage.getItem("loggedInUser");
  return data ? JSON.parse(data) : null;
}

function saveUser(user) {
  localStorage.setItem("loggedInUser", JSON.stringify(user));
}

function logout() {
  localStorage.removeItem("loggedInUser");
  currentUser = null;
  $("appPage").classList.add("hidden");
  $("loginPage").classList.remove("hidden");
}

async function request(path, method = "GET", body = null) {
  const options = {
    method,
    headers: { "Content-Type": "application/json" }
  };

  if (body) {
    options.body = JSON.stringify(body);
  }

  const res = await fetch(API_BASE + path, options);
  const data = await res.json().catch(() => ({}));

  if (!res.ok) {
    throw new Error(data.message || "Request failed");
  }

  return data;
}

async function handleLogin(e) {
  e.preventDefault();
  hideError();

  const email = $("email").value.trim();
  const password = $("password").value;
  const btn = $("loginBtn");

  if (!email.endsWith("@company.com")) {
    $("email").classList.add("error");
    $("emailError").classList.add("show");
    return;
  }

  $("email").classList.remove("error");
  $("emailError").classList.remove("show");

  btn.classList.add("loading");
  btn.textContent = "Signing in…";

  try {
    const data = await request("/auth/login", "POST", { email, password });

    saveUser(data.user || data);
    currentUser = data.user || data;

    initApp();
    toast("Welcome back, " + currentUser.name + "!", "success");

  } catch (err) {
    showError(err.message || "Invalid email or password");
  } finally {
    btn.classList.remove("loading");
    btn.textContent = "Sign in";
  }
}

function showError(msg) {
  const box = $("errorBox");
  box.textContent = msg;
  box.style.display = "block";
}

function hideError() {
  const box = $("errorBox");
  if (box) {
    box.style.display = "none";
  }
}

function togglePassword() {
  const pw = $("password");
  const icon = $("eyeIcon");

  if (pw.type === "password") {
    pw.type = "text";
    icon.innerHTML = `
      <path d="M2 2l12 12M6.5 6.6A2 2 0 0110 9.5" stroke="currentColor" stroke-width="1.3" stroke-linecap="round"/>
      <path d="M1.5 8C3 5 5.3 3.5 8 3.5c1 0 2 .2 2.9.7M11.5 5.8C13 6.9 14 7.8 14.5 8c-1.5 3-3.8 4.5-6.5 4.5a6.5 6.5 0 01-2.8-.7" stroke="currentColor" stroke-width="1.3" stroke-linecap="round"/>
    `;
  } else {
    pw.type = "password";
    icon.innerHTML = `
      <path d="M1.5 8C3 5 5.3 3.5 8 3.5S13 5 14.5 8c-1.5 3-3.8 4.5-6.5 4.5S3 11 1.5 8z" stroke="currentColor" stroke-width="1.3"/>
      <circle cx="8" cy="8" r="2" stroke="currentColor" stroke-width="1.3"/>
    `;
  }
}

function initApp() {
  currentUser = getUser();

  if (!currentUser) {
    $("loginPage").classList.remove("hidden");
    $("appPage").classList.add("hidden");
    return;
  }

  $("loginPage").classList.add("hidden");
  $("appPage").classList.remove("hidden");

  $("sideUserName").innerText = currentUser.name;
  $("sideUserRole").innerText = currentUser.role;
  $("userAvatar").innerText = getInitials(currentUser.name);
  $("profileName").innerText = currentUser.name;
  $("profileEmail").innerText = currentUser.email;
  $("profileRole").innerText = currentUser.role;

  setupRoleNav();
  showPage("dashboardPage");
  loadDashboardClaims();
}

function getInitials(name) {
  return name.split(" ").map(x => x[0]).join("").slice(0, 2).toUpperCase();
}

function setupRoleNav() {
  ["navSubmit", "navMyClaims", "navReview", "navUsers", "topNewClaimBtn"].forEach(id => {
    $(id).classList.add("hidden");
  });

  if (currentUser.role === "EMPLOYEE") {
    $("navSubmit").classList.remove("hidden");
    $("navMyClaims").classList.remove("hidden");
    $("topNewClaimBtn").classList.remove("hidden");
  }

  if (currentUser.role === "MANAGER") {
    $("navReview").classList.remove("hidden");
  }

  if (currentUser.role === "ADMIN") {
    $("navReview").classList.remove("hidden");
    $("navUsers").classList.remove("hidden");
  }
}

function showPage(pageId) {
  document.querySelectorAll(".page-section").forEach(p => p.classList.add("hidden"));
  $(pageId).classList.remove("hidden");

  document.querySelectorAll(".nav-link").forEach(n => n.classList.remove("active"));

  if (pageId === "dashboardPage") $("navDashboard").classList.add("active");
  if (pageId === "submitPage") $("navSubmit").classList.add("active");
  if (pageId === "myClaimsPage") {
    $("navMyClaims").classList.add("active");
    loadMyClaims();
  }
  if (pageId === "reviewPage") {
    $("navReview").classList.add("active");
    loadReviewClaims();
  }
  if (pageId === "usersPage") {
    $("navUsers").classList.add("active");
    loadUsers();
  }

  const titles = {
    dashboardPage: "Dashboard",
    submitPage: "Submit Claim",
    myClaimsPage: "My Claims",
    reviewPage: "Review Claims",
    usersPage: "Users",
    profilePage: "Profile"
  };

  $("pageTitle").innerText = titles[pageId] || "Dashboard";
}

function badge(status) {
  const cls = {
    SUBMITTED: "badge-submitted",
    APPROVED: "badge-approved",
    REJECTED: "badge-rejected"
  }[status] || "";

  return `<span class="badge ${cls}">${status}</span>`;
}

function currency(value) {
  return "₹" + Number(value || 0).toLocaleString("en-IN");
}

async function loadDashboardClaims() {
  try {
    let data;

    if (currentUser.role === "EMPLOYEE") {
      data = await request(`/employee/claims/${currentUser.id}?page=0&size=100`);
    } else {
      data = await request(`/reviewer/claims?reviewerId=${currentUser.id}&page=0&size=100`);
    }

    dashboardClaims = data.content || data.data?.content || data.data || data;
    renderDashboardStats(dashboardClaims);
    renderDashboardClaims(dashboardClaims);
  } catch (e) {
    $("dashboardClaimsBody").innerHTML = `<tr><td colspan="6">Error loading claims</td></tr>`;
  }
}

function renderDashboardStats(claims) {
  const total = claims.length;
  const submitted = claims.filter(c => c.status === "SUBMITTED").length;
  const approved = claims.filter(c => c.status === "APPROVED").length;
  const amount = claims
    .filter(c => c.status === "APPROVED")
    .reduce((s, c) => s + Number(c.amount), 0);

  $("statTotal").innerText = total;
  $("statSubmitted").innerText = submitted;
  $("statApproved").innerText = approved;
  $("statAmount").innerText = currency(amount);
}

function renderDashboardClaims(claims) {
  const body = $("dashboardClaimsBody");

  if (!claims || claims.length === 0) {
    body.innerHTML = `<tr><td colspan="6">No claims found</td></tr>`;
    return;
  }

  body.innerHTML = claims.slice(0, 8).map(c => `
    <tr>
      <td>${c.id}</td>
      <td>${c.description}</td>
      <td>${currency(c.amount)}</td>
      <td>${badge(c.status)}</td>
      <td>${c.claimDate}</td>
      <td>${c.reviewerComment || "-"}</td>
    </tr>
  `).join("");
}

function filterDashboardClaims(status) {
  document.querySelectorAll(".filter-chip").forEach(b => b.classList.remove("active"));
  event.target.classList.add("active");

  if (status === "ALL") {
    renderDashboardClaims(dashboardClaims);
  } else {
    renderDashboardClaims(dashboardClaims.filter(c => c.status === status));
  }
}

async function submitClaim() {
  const body = {
    amount: Number($("claimAmount").value),
    claimDate: $("claimDate").value,
    description: $("claimDescription").value.trim()
  };

  if (!body.amount || body.amount <= 0 || !body.claimDate || !body.description) {
    toast("All fields required and amount must be positive", "error");
    return;
  }

  try {
    await request(`/employee/claims/${currentUser.id}`, "POST", body);
    $("claimAmount").value = "";
    $("claimDate").value = "";
    $("claimDescription").value = "";
    toast("Claim submitted successfully");
    loadDashboardClaims();
  } catch (e) {
    toast(e.message, "error");
  }
}

async function loadMyClaims() {
  try {
    const data = await request(`/employee/claims/${currentUser.id}?page=${employeePage}&size=${pageSize}`);
    const claims = data.content || data.data?.content || data.data || data;
    renderSimpleClaims(claims, "myClaimsWrap");
    $("employeePageText").innerText = `Page ${employeePage}`;
  } catch {
    $("myClaimsWrap").innerHTML = `<div class="empty-state">Error while loading my claims</div>`;
  }
}

function renderSimpleClaims(claims, wrapId) {
  const wrap = $(wrapId);

  if (!claims || claims.length === 0) {
    wrap.innerHTML = `<div class="empty-state">No claims found</div>`;
    return;
  }

  wrap.innerHTML = `
    <table>
      <thead>
        <tr>
          <th>ID</th><th>Description</th><th>Amount</th><th>Status</th><th>Date</th><th>Comment</th>
        </tr>
      </thead>
      <tbody>
        ${claims.map(c => `
          <tr>
            <td>${c.id}</td>
            <td>${c.description}</td>
            <td>${currency(c.amount)}</td>
            <td>${badge(c.status)}</td>
            <td>${c.claimDate}</td>
            <td>${c.reviewerComment || "-"}</td>
          </tr>
        `).join("")}
      </tbody>
    </table>
  `;
}

function nextEmployeePage() {
  employeePage++;
  loadMyClaims();
}

function prevEmployeePage() {
  if (employeePage > 0) {
    employeePage--;
    loadMyClaims();
  }
}

async function loadReviewClaims() {
  try {
    const data = await request(`/reviewer/claims?reviewerId=${currentUser.id}&page=${reviewPage}&size=${pageSize}`);
    const claims = data.content || data.data?.content || data.data || data;
    renderReviewClaims(claims);
    $("reviewPageText").innerText = `Page ${reviewPage}`;
  } catch {
    $("reviewClaimsWrap").innerHTML = `<div class="empty-state">Error while loading claims</div>`;
  }
}

function renderReviewClaims(claims) {
  const wrap = $("reviewClaimsWrap");

  if (!claims || claims.length === 0) {
    wrap.innerHTML = `<div class="empty-state">No claims found</div>`;
    return;
  }

  wrap.innerHTML = `
    <table>
      <thead>
        <tr>
          <th>ID</th><th>Employee</th><th>Description</th><th>Amount</th><th>Status</th><th>Comment</th><th>Action</th>
        </tr>
      </thead>
      <tbody>
        ${claims.map(c => {
          const disabled = c.status !== "SUBMITTED" ? "disabled" : "";
          return `
            <tr>
              <td>${c.id}</td>
              <td>${c.employeeId}</td>
              <td>${c.description}</td>
              <td>${currency(c.amount)}</td>
              <td>${badge(c.status)}</td>
              <td><input class="review-input" id="comment-${c.id}" value="${c.reviewerComment || ""}" ${disabled}></td>
              <td>
                <button class="approve-btn" onclick="approveClaim(${c.id})" ${disabled}>Approve</button>
                <button class="reject-btn" onclick="rejectClaim(${c.id})" ${disabled}>Reject</button>
              </td>
            </tr>
          `;
        }).join("")}
      </tbody>
    </table>
  `;
}

async function approveClaim(claimId) {
  const comment = $(`comment-${claimId}`).value.trim();

  try {
    await request(`/reviewer/claims/${claimId}/approve?reviewerId=${currentUser.id}`, "PUT", { comment });
    toast("Claim approved successfully");
    loadReviewClaims();
    loadDashboardClaims();
  } catch (e) {
    toast(e.message, "error");
  }
}

async function rejectClaim(claimId) {
  const comment = $(`comment-${claimId}`).value.trim();

  if (!comment) {
    toast("Comment is required for rejection", "error");
    return;
  }

  try {
    await request(`/reviewer/claims/${claimId}/reject?reviewerId=${currentUser.id}`, "PUT", { comment });
    toast("Claim rejected successfully");
    loadReviewClaims();
    loadDashboardClaims();
  } catch (e) {
    toast(e.message, "error");
  }
}

function nextReviewPage() {
  reviewPage++;
  loadReviewClaims();
}

function prevReviewPage() {
  if (reviewPage > 0) {
    reviewPage--;
    loadReviewClaims();
  }
}

async function createUser() {
  const body = {
    name: $("userName").value.trim(),
    email: $("userEmail").value.trim(),
    password: $("userPassword").value.trim(),
    role: $("userRole").value
  };

  const managerId = $("userManagerId").value.trim();

  if (!body.name || !body.email || !body.password) {
    toast("All fields required", "error");
    return;
  }

  if (!body.email.endsWith("@company.com")) {
    toast("Only company email allowed", "error");
    return;
  }

  if (managerId) {
    body.managerId = Number(managerId);
  }

  try {
    await request("/admin/users", "POST", body);
    toast("User created successfully");
    loadUsers();
  } catch (e) {
    toast(e.message, "error");
  }
}

async function loadUsers() {
  try {
    const data = await request("/admin/users");
    const users = data.data || data;

    if (!users || users.length === 0) {
      $("usersWrap").innerHTML = `<div class="empty-state">No users found</div>`;
      return;
    }

    $("usersWrap").innerHTML = `
      <table>
        <thead>
          <tr>
            <th>ID</th><th>Name</th><th>Email</th><th>Role</th><th>Manager ID</th><th>Action</th>
          </tr>
        </thead>
        <tbody>
          ${users.map(u => `
            <tr>
              <td>${u.id}</td>
              <td>${u.name}</td>
              <td>${u.email}</td>
              <td>${u.role}</td>
              <td>${u.managerId || "-"}</td>
              <td><button class="btn btn-danger btn-sm" onclick="deleteUser(${u.id})">Delete</button></td>
            </tr>
          `).join("")}
        </tbody>
      </table>
    `;
  } catch {
    $("usersWrap").innerHTML = `<div class="empty-state">Error loading users</div>`;
  }
}

async function deleteUser(id) {
  try {
    await request(`/admin/users/${id}`, "DELETE");
    toast("User deleted successfully");
    loadUsers();
  } catch (e) {
    toast(e.message, "error");
  }
}

async function assignManager() {
  const employeeId = $("assignEmployeeId").value;
  const managerId = $("assignManagerId").value;

  if (!employeeId || !managerId) {
    toast("Employee ID and Manager ID required", "error");
    return;
  }

  try {
    await request(`/admin/users/${employeeId}/manager/${managerId}`, "PUT");
    toast("Manager assigned successfully");
    loadUsers();
  } catch (e) {
    toast(e.message, "error");
  }
}

window.onload = initApp;