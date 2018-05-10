var express = require('express');
var router = express.Router();
var fs = require("fs");


/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/pedo.do',function(req,res){
//   fs.readFile( __dirname + "/" + "users.json", 'utf8', function (err, data) {
       console.log( data );
  //     res.end( data );
  // });
});

module.exports = router;
