/* global use, db */
// MongoDB Playground
// To disable this template go to Settings | MongoDB | Use Default Template For Playground.
// Make sure you are connected to enable completions and to be able to run a playground.
// Use Ctrl+Space inside a snippet or a string literal to trigger completions.
// The result of the last command run in a playground is shown on the results panel.
// By default the first 20 documents will be returned with a cursor.
// Use 'console.log()' to print to the debug output.
// For more documentation on playgrounds please refer to
// https://www.mongodb.com/docs/mongodb-vscode/playgrounds/

// Select the database to use.
use('GeekTextDB');

// delete some docs from collection to avoid duplicates
db.getCollection('shopping_carts').deleteMany({});

//add documents into shopping carts collection
db.getCollection('shopping_carts').insertMany([
  {
    'userId': 'user001',
    'books': [
      {
        '_id': '67032d5e7d9131561dfedf00',
        'title': 'Binti',
        'author': 'Nnedi Okorafor',
        'price': 9.99
      }
    ], 
    'createdAt': new Date(),
    'updatedAt': new Date()
  }, 
  {
    'userId': 'user002',
    'books': [
      {
        '_id': '67032d5e7d9131561dfedf01',
        'title': 'Binti: Home',
        'author': 'Nnedi Okorafor',
        'price': 9.99
      },
      {
        '_id': '67032d5e7d9131561dfedf01',
        'title': 'Binti: Home',
        'author': 'Nnedi Okorafor',
        'price': 11.99
      }
    ],
    'createdAt': new Date(),
    'updatedAt': new Date()
  },
  {
    'userId': 'user003',
    'books': [
      {
        '_id': '67032d5e7d9131561dfedf02',
        'title': 'Binti: The Night Masquerade',
        'author': 'Nnedi Okorafor',
        'price': 14.99
      },
      {
        '_id': '67032d5e7d9131561dfedf02',
        'title': 'Binti: The Night Masquerade',
        'author': 'Nnedi Okorafor',
        'price': 14.99
      }
    ],
    'createdAt': new Date(),
    'updatedAt': new Date()
  }
]);

//checks added documents
const addedItems = db.getCollection('shopping_carts').countDocuments();
console.log(`${addedItems} shopping carts have been inserted`);


//query for shopping carts w/ info
const allCarts = db.getCollection('shopping_carts').find().toArray();
console.log('All shopping carts:');
allCarts.forEach(cart => {
  console.log(`User ID: ${cart.userId}`);
  console.log('Books:');
  cart.books.forEach(book => {
    console.log(`-${book.title} by ${book.author}, Price: $${book.price}`);
  });
  console.log('------------------------------------------------------');
})