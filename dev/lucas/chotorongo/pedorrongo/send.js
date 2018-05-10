var nodemailer = require('nodemailer');



var transporter = nodemailer.createTransport({
  service: 'gmail',
  auth: {
    user: 'panella.dante@gmail.com',
    pass: 'Fogonazo65'
  }
});

var mailOptions = {
  from: 'panella.dante@gmail.com',
  to: 'panella.dante@gmail.com',
  subject: 'Sending Email using Node.js',
  text: 'That was easy!'
};

transporter.sendMail(mailOptions, function(error, info){
  document.write("entro were");
    if (error) {
    console.log(error);
    document.write("pedo");

  } else {
    console.log('Email sent: ' + info.response);
    document.write("enviado");
}
});