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

// Insert a few documents into the books collection.
db.getCollection('books').insertMany([
  {
    'title': 'Binti',
    'author': 'Nnedi Okorafor',
    'price': '9.99',
    'isbn': '978-0765385253',
    'description': 'Her name is Binti and she is the first of the Himba people ever to be offered a place at Oomza University, the finest institution of higher learning in the galaxy.',
    'publisher': 'Tor.com',
    'publicationDate': new Date('2015-09-22'),
    'genre': 'Science Fiction'
  }, 
  {
    'title': 'Binti: Home',
    'author': 'Nnedi Okorafor',
    'price': '11.99',
    'isbn': '978-0765393111',
    'description': 'Its been a year since Binti and Okwu enrolled at Oomza University. A year since Binti was declared a hero for uniting two warring planets',
    'publisher': 'Tor.com',
    'publicationDate': new Date('2017-01-31'),
    'genre': 'Science Fiction'
  },
  {
    'title': 'Binti: The Night Masquerade',
    'author': 'Nnedi Okorafor',
    'price': '11.99',
    'isbn': '978-0765393135',
    'description': 'Binti has returned to her home planet, believing that the violence of the Meduse has been left behind.',
    'publisher': 'Tor.com',
    'publicationDate': new Date('2015-09-22'),
    'genre': 'Science Fiction'
  }
  
]); 

db.books.find().forEach(function(doc){
  db.books.updateMany(
    {_id: doc._id},
    { $set: { bookId: doc._id.toString()} }
  );
});

// Run a find command to view items sold on April 4th, 2014.
const booksCount = db.getCollection('books').countDocuments();
console.log(`${booksCount} books have been inserted.`);

//query to retrieve books
const allBooks = db.getCollection('books').find().toArray();
console.log('All books', allBooks);
