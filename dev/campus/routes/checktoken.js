var express = require('express');
var router = express.Router();
var jwt = require('jsonwebtoken'); // used to create, sign, and verify tokens

//var token = jwt.sign({ foo: 'bar' }, 'SuperSecret');
var token;
/* GET users listing. */
router.get('/', function (req, res, next) {

//console.log("token " +  req.param("token"));
var cert=req.param("token");

var token = jwt.sign({ foo: 'bar' }, cert);
    // verify a token symmetric
    jwt.verify(token, cert, function (err, decoded) {
        console.log(decoded.foo) // bar
    });

});



router.post('/api/posts',verifyToken,(req,res)=>{

    jwt.verify(req.token,'secretkey',(err,authData)=>{
        console.log("Erroros: " +err)
        console.log("Data: " +authData);


        if(err)
        {
            res.sendStatus(403);        
        }else{
            res.json({
                message:'post created...',
                authData
            });
        }
    })
    
})

router.post('/login',(req,resp)=>{

    const user = {
        id:1,
        name:'chotozo',
        email:'chotozo@gmail.com'
    }
    jwt.sign({user},'secretkey',{expiresIn:'20s'},(err,token)=>{

        resp.json({
            token
        });
    });
})

//verifico el token
function verifyToken(req, res, next){

    const bearerHeader = req.headers['authorization'];

    if(typeof bearerHeader !== 'undefined'){

        //en la 1 posicion del split del bearedHeader viene el token
        const bearer = bearerHeader.split(' ');
         
        const bearerToken = bearer[1];
        req.token=bearerToken;
        next();

    }else{
        //no access
        res.sendStatus(403)
    }
}

module.exports = router;