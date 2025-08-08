// Application State
let isLoggedIn = false;
let currentUser = null;
let cart = [];

// DOM Elements
const loginForm = document.getElementById('loginForm');
const userMenu = document.getElementById('userMenu');
const userGreeting = document.getElementById('userGreeting');
const userIcon = document.getElementById('userIcon');
const cartBadge = document.getElementById('cartBadge');
const cartItems = document.getElementById('cartItems');
const cartTotal = document.getElementById('cartTotal');
const checkoutBtn = document.getElementById('checkoutBtn');

// Login Functionality
document.getElementById('loginFormElement').addEventListener('submit', function (e) {
    e.preventDefault();
    const email = document.getElementById('loginEmail').value;
    const password = document.getElementById('loginPassword').value;

    // Simple demo login (in real app, this would be an API call)
    if (email && password) {
        login({
            name: email.split('@')[0],
            email: email
        });

        // Close dropdown
        const dropdown = bootstrap.Dropdown.getInstance(document.getElementById('userDropdown'));
        if (dropdown) dropdown.hide();
    }
});

// Register Button
document.getElementById('registerBtn').addEventListener('click', function () {
    alert('Registration form would open here!');
});

// Logout
document.getElementById('logoutBtn').addEventListener('click', function (e) {
    e.preventDefault();
    logout();
});

// Login Function
function login(user) {
    isLoggedIn = true;
    currentUser = user;

    // Update UI
    loginForm.classList.add('d-none');
    userMenu.classList.remove('d-none');
    userGreeting.textContent = `Hi, ${user.name}`;
    userIcon.className = 'fas fa-user-circle fa-lg me-1 text-success';

    // Clear form
    document.getElementById('loginEmail').value = '';
    document.getElementById('loginPassword').value = '';

    showNotification('Successfully logged in!', 'success');
}

// Logout Function
function logout() {
    isLoggedIn = false;
    currentUser = null;

    // Update UI
    loginForm.classList.remove('d-none');
    userMenu.classList.add('d-none');
    userGreeting.textContent = 'Account';
    userIcon.className = 'fas fa-user-circle fa-lg me-1';

    showNotification('Successfully logged out!', 'info');
}

// Search Functionality
document.getElementById('searchBtn').addEventListener('click', performSearch);
document.getElementById('searchInput').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
        performSearch();
    }
});

function performSearch() {
    const query = document.getElementById('searchInput').value.trim();
    if (query) {
        showNotification(`Searching for: "${query}"`, 'info');
        // In a real app, this would trigger a search API call
    }
}

// Cart Functionality
document.addEventListener('click', function (e) {
    if (e.target.classList.contains('add-to-cart')) {
        const name = e.target.getAttribute('data-name');
        const price = parseFloat(e.target.getAttribute('data-price'));
        addToCart({ name, price });
    }
});

function addToCart(item) {
    // Check if item already exists
    const existingItem = cart.find(cartItem => cartItem.name === item.name);

    if (existingItem) {
        existingItem.quantity += 1;
    } else {
        cart.push({ ...item, quantity: 1 });
    }

    updateCartUI();
    showNotification(`${item.name} added to cart!`, 'success');
}

function removeFromCart(itemName) {
    cart = cart.filter(item => item.name !== itemName);
    updateCartUI();
    showNotification('Item removed from cart', 'info');
}

function updateCartUI() {
    // Update badge
    const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
    cartBadge.textContent = totalItems;
    cartBadge.style.display = totalItems > 0 ? 'flex' : 'none';

    // Update cart items
    if (cart.length === 0) {
        cartItems.innerHTML = '<p class="text-muted mb-0">Your cart is empty</p>';
        checkoutBtn.disabled = true;
    } else {
        cartItems.innerHTML = cart.map(item => `
                    <div class="cart-item">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <div class="fw-bold">${item.name}</div>
                                <small class="text-muted">$${item.price.toFixed(2)} x ${item.quantity}</small>
                            </div>
                            <button class="btn btn-sm btn-outline-danger" onclick="removeFromCart('${item.name}')">
                                <i class="fas fa-trash"></i>
                            </button>
                        </div>
                    </div>
                `).join('');
        checkoutBtn.disabled = false;
    }

    // Update total
    const total = cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);
    cartTotal.textContent = total.toFixed(2);
}

// Checkout
checkoutBtn.addEventListener('click', function () {
    if (!isLoggedIn) {
        showNotification('Please log in to checkout', 'warning');
        return;
    }

    showNotification('Redirecting to checkout...', 'info');
    // In a real app, this would redirect to checkout page
});

// Notification System
function showNotification(message, type = 'info') {
    // Create notification element
    const notification = document.createElement('div');
    notification.className = `alert alert-${type} alert-dismissible fade show position-fixed`;
    notification.style.cssText = 'top: 20px; right: 20px; z-index: 9999; min-width: 300px;';
    notification.innerHTML = `
                ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            `;

    document.body.appendChild(notification);

    // Auto remove after 3 seconds
    setTimeout(() => {
        if (notification.parentNode) {
            notification.remove();
        }
    }, 3000);
}

// Initialize
updateCartUI();