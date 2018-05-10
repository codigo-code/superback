var express = require('express');
var router = express.Router();
var gmailNode = require('gmail-node');

var testMessage = {
    to: 'guptkashish@gmail.com',
    subject: 'Test Subject',
    message: '<h1>Test Email</h1>'
};


var clientSecret = {
    installed: {
        client_id: "1062778371602-vbfcphkh9s6v6qds3akvb80n06rjid4f.apps.googleusercontent.com",
        project_id: "QRealStateApp",
        auth_uri: "https://accounts.google.com/o/oauth2/auth",
        token_uri: "https://accounts.google.com/o/oauth2/token",
        auth_provider_x509_cert_url: "https://www.googleapis.com/oauth2/v1/certs",
        client_secret: "a4mmtphQbLkopgCWwF1Gp0hL",
        redirect_uris: [
            "urn:ietf:wg:oauth:2.0:oob",
            "http://localhost"
        ]
    }
};


router.get('/', function(req, res, next) {
    

    gmailNode.init(clientSecret, './token.json', initComplete);
    
    function initComplete(err, dataObject) {
        if(err){
            console.log('Error ', err);
        }else {
            gmailNode.send(testMessage, function (err, data) {
                console.log(err,data);
            });
            
            // OR
            
            gmailNode.sendHTML(testMessage, function (err, data) {
                console.log(err,data);
            });
        }
    }

    
    res.render('index', { title: 'Express' });


  });


module.exports = router;
