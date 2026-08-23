// Initial product list
let products = [
    { id: 1, name: "Laptop", price: 55000, stock: 5, category: "electronics" },
    { id: 2, name: "Shirt", price: 1200, stock: 10, category: "clothing" },
    { id: 3, name: "Book", price: 500, stock: 0, category: "books" },
    { id: 4, name: "Headphones", price: 2000, stock: 3, category: "electronics" },
    { id: 5, name: "Watch", price: 1500, stock: 7, category: "accessories" }
];

// Load from localStorage if available
const saved = localStorage.getItem("products");
if (saved) {
    products = JSON.parse(saved);
}

// Save data
function saveData() {
    localStorage.setItem("products", JSON.stringify(products));
}

// Render products
function renderProducts(list) {
    const container = document.getElementById("productContainer");
    container.innerHTML = "";

    if (list.length === 0) {
        container.innerHTML = "<p>No products found</p>";
        return;
    }

    list.forEach(p => {
        const card = document.createElement("div");

        card.innerHTML = `
            <h3>${p.name}</h3>
            <p>Price: ₹${p.price}</p>
            <p>Stock: ${p.stock}</p>
            <p>Category: ${p.category}</p>
            <button onclick="deleteProduct(${p.id})">Delete</button>
        `;

        container.appendChild(card);
    });

    updateAnalytics(list);
}

// Delete product
function deleteProduct(id) {
    products = products.filter(p => p.id !== id);
    saveData();
    renderProducts(products);
}

// Add product
function addProduct() {
    const name = document.getElementById("name").value;
    const price = document.getElementById("price").value;
    const stock = document.getElementById("stock").value;
    const category = document.getElementById("category").value;

    if (!name || price <= 0 || stock < 0 || !category) {
        alert("Please enter valid data");
        return;
    }

    const newProduct = {
        id: Date.now(),
        name,
        price: Number(price),
        stock: Number(stock),
        category
    };

    products.push(newProduct);
    saveData();
    renderProducts(products);
}

// Analytics
function updateAnalytics(list) {
    document.getElementById("totalProducts").innerText = list.length;

    let totalValue = 0;
    let outStock = 0;

    list.forEach(p => {
        totalValue += p.price * p.stock;
        if (p.stock === 0) outStock++;
    });

    document.getElementById("totalValue").innerText = totalValue;
    document.getElementById("outOfStock").innerText = outStock;
}

// Search
document.getElementById("searchInput").addEventListener("input", function () {
    const value = this.value.toLowerCase();

    const filtered = products.filter(p =>
        p.name.toLowerCase().includes(value)
    );

    renderProducts(filtered);
});

// Category filter
document.getElementById("categoryFilter").addEventListener("change", function () {
    let value = this.value;

    if (value === "all") {
        renderProducts(products);
    } else {
        const filtered = products.filter(p => p.category === value);
        renderProducts(filtered);
    }
});

// Sorting
document.getElementById("sortOption").addEventListener("change", function () {
    let value = this.value;
    let sorted = [...products];

    if (value === "low") sorted.sort((a, b) => a.price - b.price);
    if (value === "high") sorted.sort((a, b) => b.price - a.price);
    if (value === "az") sorted.sort((a, b) => a.name.localeCompare(b.name));
    if (value === "za") sorted.sort((a, b) => b.name.localeCompare(a.name));

    renderProducts(sorted);
});

// Low stock
document.getElementById("lowStockBtn").addEventListener("click", function () {
    const filtered = products.filter(p => p.stock < 5);
    renderProducts(filtered);
});

// Loading simulation
function fetchProducts() {
    return new Promise(resolve => {
        setTimeout(() => {
            resolve(products);
        }, 1500);
    });
}

async function init() {
    document.getElementById("loading").style.display = "block";

    const data = await fetchProducts();

    document.getElementById("loading").style.display = "none";

    renderProducts(data);
}

init();