# Product Inventory Dashboard

## Project Overview
This project is a simple Product Inventory Dashboard developed using pure HTML, CSS, and JavaScript.  
The main goal of this project is to manage product data dynamically without using any external libraries or frameworks.

In this application, users can add, delete, search, filter, and sort products. The data is stored in the browser using localStorage so that it remains saved even after refreshing the page.

---

## Features Implemented

### 1. Dynamic Product Rendering
All products are rendered using JavaScript dynamically.  
No product is hardcoded in HTML.

---

### 2. Add Product
Users can add a new product using the form.

Validation applied:
- Product name cannot be empty
- Price must be greater than 0
- Stock cannot be negative
- Category must be selected

After adding:
- Product is displayed instantly
- Form fields are cleared
- Analytics section updates automatically

---

### 3. Delete Product
Each product card has a delete button.

When clicked:
- Product is removed from UI
- Data updates in localStorage
- Analytics updates immediately

---

### 4. Search Functionality
Users can search products by name.

- Case insensitive search
- Works in real-time while typing

Example:
Typing "lap" → shows "Laptop"

---

### 5. Category Filter
Products can be filtered based on category.

Available options:
- All Categories
- Electronics
- Clothing
- Books
- Accessories

---

### 6. Low Stock Filter
Displays products where stock is less than 5.

---

### 7. Sorting Feature
Products can be sorted based on:

- Price (Low → High)
- Price (High → Low)
- Alphabetically (A → Z)
- Alphabetically (Z → A)

---

### 8. Inventory Analytics
Dashboard shows:

- Total number of products
- Total inventory value (price × stock)
- Number of out-of-stock products

---

### 9. Data Persistence (localStorage)
All product data is stored in browser localStorage.

- Data remains after refresh
- If no data exists, default products are loaded

---

### 10. Loading Simulation (Async Concept)
A loading message is displayed when the page loads.

- Simulated using Promise and setTimeout
- After delay, products are rendered

---

## Technologies Used

- HTML → Structure
- CSS → Styling and layout
- JavaScript → Logic and dynamic behavior
- localStorage → Data persistence

---

