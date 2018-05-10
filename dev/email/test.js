var GoogleContacts = require('google-contacts').GoogleContacts;
var c = new GoogleContacts({
  token: 'ya29.GlutBR4xPS1IHJWW7UwPnrJuSIg504htj4xTHXVOrSn1QxwTYPZSyoo1q2C4No7b_6r4GxrFF4c1EbesJtqpH3JDBMMzxfI-5OfGWZ4ck7KsFVq3ChTKVu3_gHby'
});
 

console.log(c);

c.getContacts(c, c.params);
