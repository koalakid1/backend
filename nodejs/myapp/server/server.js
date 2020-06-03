const express = require('express');
const path = require('path');
const router = require("./routes/router");

const app = express();
const PORT = process.env.PORT || 4000;

app.use(express.static(path.join(__dirname, '..', 'public/')));

app.use("/", router);
const morgan = require('morgan');

app.use(morgan('combined'));

app.listen(PORT, () => {
    console.log(`Check out the app at http://localhost:${PORT}`);
});


