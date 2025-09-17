const newman = require('newman');
require('dotenv').config();

newman.run({
  collection: require('./API-TechMarket.postman_collection.json'),
  environment: require('./TechMarket Enviroment.postman_environment.json'),
  reporters: ['cli', 'json', 'html'],
  reporter: {
    html: {
      export: './newman/report.html'
    },
    json: {
      export: './newman/report.json'
    }
  }
}, function (err, summary) {
  if (err) { throw err; }
  console.log('Pruebas de TechMarket completadas!');
});