var nodemailer = require('nodemailer');

var transporter = nodemailer.createTransport({
  service: 'Naver',
  host: 'smtp.naver.com',
  port: 587,
  auth: {
    user: 'koalakid@naver.com',
    pass: '539715asdf!'
  }
});

var mailOptions = {
  from: 'koalakid@naver.com',
  to: 'koalakid@naver.com',
  subject: 'Sending Email using Node.js',
  text: 'That was easy!'
};

transporter.sendMail(mailOptions, function(error, info){
  if (error) {
    console.log(error);
  } else {
    console.log('Email sent: ' + info.response);
  }
});