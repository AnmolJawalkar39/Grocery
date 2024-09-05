const express = require('express');
const app = express();
app.use(express.json());
 
let users = [
{ id: 1, username: 'user1', password: 'pass1', email: 'user1@example.com' },
{ id: 2, username: 'user2', password: 'pass2', email: 'user2@example.com' },
{ id: 3, username: 'user3', password: 'pass3', email: 'user3@example.com' },
{ id: 4, username: 'user4', password: 'pass4', email: 'user4@example.com' },
{ id: 5, username: 'user5', password: 'pass5', email: 'user5@example.com' }
];
 
let products = [
{ id: 1, name: 'Product1', category: 'Category1', price: 10.0, stockQuantity: 100 },
{ id: 2, name: 'Product2', category: 'Category2', price: 20.0, stockQuantity: 200 },
{ id: 3, name: 'Product3', category: 'Category3', price: 30.0, stockQuantity: 300 },
{ id: 4, name: 'Product4', category: 'Category4', price: 40.0, stockQuantity: 400 },
{ id: 5, name: 'Product5', category: 'Category5', price: 50.0, stockQuantity: 500 }
];
 
let orders = [
{ id: 1, userId: 1, productId: 1, quantity: 2, totalPrice: 20.0 },
{ id: 2, userId: 2, productId: 2, quantity: 3, totalPrice: 60.0 },
{ id: 3, userId: 3, productId: 3, quantity: 4, totalPrice: 120.0 },
{ id: 4, userId: 4, productId: 4, quantity: 5, totalPrice: 200.0 },
{ id: 5, userId: 5, productId: 5, quantity: 6, totalPrice: 300.0 }
];

// User Authentication Endpoints
app.post('/users/register', (req, res) => {
const { username, password, email } = req.body;
const newUser = { id: users.length + 1, username, password, email };
users.push(newUser);
res.status(201).json(newUser);
});
 
app.post('/users/login', (req, res) => {
const { username, password } = req.body;
const user = users.find(u => u.username === username && u.password === password);
if (user) {
res.status(200).json({ message: 'Login successful' });
} else {
res.status(401).json({ message: 'Invalid credentials' });
}
});
// Product Management Endpoints
app.post('/products', (req, res) => {
const { name, category, price, stockQuantity } = req.body;
const newProduct = { id: products.length + 1, name, category, price, stockQuantity };
products.push(newProduct);
res.status(201).json(newProduct);
});
 
//app.get('/products/:productId', (req, res) => {
//const product = products.find(p => p.id === parseInt//(req.params.productId));
//if (product) {
//res.status(200).json(product);
//} else {
//res.status(404).json({ message: 'Product not found' });
//}
//});
// New endpoint to search for products by name
app.get('/products/search', (req, res) => {
  const query = req.query.name;
  if (!query) {
    return res.status(400).json({ message: 'Query parameter "name" is required' });
  }

  const results = products.filter(p => p.name.toLowerCase().includes(query.toLowerCase()));
  res.status(200).json(results);
});
 
// Order Processing Endpoints
app.post('/orders', (req, res) => {
const { userId, productId, quantity, totalPrice } = req.body;
const newOrder = { id: orders.length + 1, userId, productId, quantity, totalPrice };
orders.push(newOrder);
res.status(201).json(newOrder);
});
app.get('/orders/:orderId', (req, res) => {
const order = orders.find(o => o.id === parseInt(req.params.orderId));
if (order) {
res.status(200).json(order);
} else {
res.status(404).json({ message: 'Order not found' });
}
});
 
// User Profile Management Endpoints
app.put('/users/:userId', (req, res) => {
const user = users.find(u => u.id === parseInt(req.params.userId));
if (user) {
const { username, email } = req.body;
user.username = username;
user.email = email;
res.status(200).json(user);
} else {
res.status(404).json({ message: 'User not found' });
}
});
app.delete('/users/:userId', (req, res) => {
const userIndex = users.findIndex(u => u.id === parseInt(req.params.userId));
if (userIndex !== -1) {
users.splice(userIndex, 1);
res.status(204).send();
} else {
res.status(404).json({ message: 'User not found' });
}
});
 
// Product Search and Filtering Endpoints
app.get('/products/search', (req, res) => {
const { name } = req.query;
const filteredProducts = products.filter(p => p.name.includes(name));
res.status(200).json(filteredProducts);
});
 
app.get('/products/filter', (req, res) => {
const { category } = req.query;
const filteredProducts = products.filter(p => p.category === category);
res.status(200).json(filteredProducts);
});
 
// Endpoint to show all data
app.get('/data', (req, res) => {
res.status(200).json({ users, products, orders });
});// Start the server
const PORT = process.env.PORT || 3838;
app.listen(PORT, () => {
console.log(Server is running on port ${PORT});
});
