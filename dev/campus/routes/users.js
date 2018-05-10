var express = require('express');
var router = express.Router();
var jwt    = require('jsonwebtoken'); // used to create, sign, and verify tokens

var app = express();

const  payload ={
	id:1,
	name:'pepe',
	lastname:'suarez',
	
};

/* GET users listing. */
router.get('/', function(req, res, next) {


	
	 var tokenizer = jwt.sign(payload, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwibmFtZSI6InBlcGUiLCJsYXN0bmFtZSI6InN1YXJleiIsImlhdCI6MTUyNDQ5ODY5N30.qK5CngqOM_WyJJkDfaIVjRUgB0NE6kXg-ZQiF9329jQ', function(err, token) {
		//console.log(token);
		//console.log(err);
		return token;
	});

	console.log(tokenizer);
	var obj = {
		success:true,
		message:'adentro',
		token:tokenizer
	};
// sign asynchronously

//return tokenizer;
 res.json(obj);
});

module.exports = router;
