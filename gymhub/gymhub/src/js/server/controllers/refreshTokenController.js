const Trainee = require('../model/Trainee');
const jwt = require('jsonwebtoken');

const handleRefreshToken = async (req, res) => {
    const cookies = req.cookies;
    if (!cookies?.jwt) return res.sendStatus(401);
    const refreshToken = cookies.jwt;

    const foundTrainee = await Trainee.findOne({ refreshToken }).exec();
    if (!foundTrainee) return res.sendStatus(403); //Forbidden 
    // evaluate jwt 
    jwt.verify(
        refreshToken,
        process.env.REFRESH_TOKEN_SECRET,
        (err, decoded) => {
            if (err || foundTrainee.user !== decoded.user) return res.sendStatus(403);
            const roles = Object.values(foundTrainee.roles);
            const accessToken = jwt.sign(
                {
                    "TraineeInfo": {
                        "user": decoded.user,
                        "roles": roles
                    }
                },
                process.env.ACCESS_TOKEN_SECRET,
                { expiresIn: '10s' }
            );
            res.json({ roles, accessToken })
        }
    );
}

module.exports = { handleRefreshToken }