const newman = require('newman');
require('dotenv').config();

newman.run({
  collection: require('./Suite de Pruebas Demo API.postman_collection.json'),
  environment: require('./DemoAPI Enviroment.postman_environment.json'),
  reporters: ['cli', 'json', 'html']
}, function (err, summary) {
  if (err) { throw err; }
  console.log('Pruebas completadas!');
});

