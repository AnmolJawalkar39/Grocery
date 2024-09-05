const express = require('express');

const app = express();

app.use(express.json());

let users = [
  { id: 1, username: 'Anmol', password: 'Abc@123', email: 'anmol@example.com' },
  { id: 2, username: 'Tushar', password: 'Def@123', email: 'tushar@example.com' },
  { id: 3, username: 'Anil', password: 'Pqr@123', email: 'anil@example.com' },
  { id: 4, username: 'Ganesh', password: 'Stu@123', email: 'ganesh@example.com' },
  { id: 5, username: 'Guru', password: 'Xyz@123', email: 'guru@example.com' }
];

let products = [
  { id: 1, name: 'Oil', category: 'Grocery1', price: 10.0, stockQuantity: 100 },
  { id: 2, name: 'Rice', category: 'Grocery2', price: 20.0, stockQuantity: 200 },
  { id: 3, name: 'Notebook', category: 'Student', price: 30.0, stockQuantity: 300 },
  { id: 4, name: 'Pen', category: 'Student1', price: 40.0, stockQuantity: 400 },
  { id: 5, name: 'Pencil', category: 'Student2', price: 50.0, stockQuantity: 500 }
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

app.get('/products/:productId', (req, res) => {
  const product = products.find(p => p.id === parseInt(req.params.productId));
  if (product) {
    res.status(200).json(product);
  } else {
    res.status(404).json({ message: 'Product not found' });
  }
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
  console.log(`Searching for products with name: ${name}`);
  const filteredProducts = products.filter(p => p.name.toLowerCase().includes(name.toLowerCase()));
  res.status(200).json(filteredProducts);
});

//Filtering Endpoints
app.get('/products/filter', (req, res) => {
  const { category } = req.query;
  console.log(`Received category for filtering: ${category}`);
  const filteredProducts = products.filter(p => p.category.toLowerCase() === category.toLowerCase());
  console.log(`Filtered products: ${JSON.stringify(filteredProducts)}`);
  
  if (filteredProducts.length > 0) {
    res.status(200).json(filteredProducts);
  } else {
    res.status(404).json({ message: 'Product not found' });
  }
});

// Endpoint to show all data
app.get('/data', (req, res) => {
  res.status(200).json({ users, products, orders });
});

// Start the server
const PORT = process.env.PORT || 3939;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
