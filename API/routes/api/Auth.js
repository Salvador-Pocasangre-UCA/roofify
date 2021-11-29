const express = require('express');
const router = express.Router();

const AuthController = require('../../controllers/api/Auth');
var upload = multer({ dest: 'uploads/'})

router.post('/signup',upload.single('image'), AuthController.register);
router.post('/signin', AuthController.login);

module.exports = router;
