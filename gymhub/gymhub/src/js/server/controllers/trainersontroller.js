const Trainer = require('../model/Trainer');

const getAllTrainers = async (req, res) => {
    const trainers = await Trainer.find();
    if (!trainers) return res.status(204).json({ 'message': 'No trainers found.' });
    res.json(trainers);
}

const createNewTrainer = async (req, res) => {
    if (!req?.body?.name || !req?.body?.user || !req?.body?.email || !req?.body?.pNumber || !req?.body?.role || !req?.body?.nic || !req?.body?.pwd) {
        return res.status(400).json({ 'message': 'All the fields are are required' });
    }

    try {
        const result = await Trainer.create({
            name: req.body.name,
            user: req.body.user,
            email: req.body.email,
            pNumber: req.body.pNumber,
            role: req.body.role,
            nic: req.body.nic,
            pwd: req.body.pwd
        });

        res.status(201).json(result);
    } catch (err) {
        console.error(err);
    }
}

const updateTrainer = async (req, res) => {
    if (!req?.body?.id) {
        return res.status(400).json({ 'message': 'ID parameter is required.' });
    }

    const trainer = await Trainer.findOne({ _id: req.body.id }).exec();
    if (!trainer) {
        return res.status(204).json({ "message": `No trainer matches ID ${req.body.id}.` });
    }
    if (req.body?.name) trainer.name = req.body.name;
    if (req.body?.user) trainer.user = req.body.user;
    if (req.body?.email) trainer.email = req.body.email;
    if (req.body?.pNumber) trainer.pNumber = req.body.pNumber;
    if (req.body?.role) trainer.role = req.body.role;
    if (req.body?.nic) trainer.nic = req.body.nic;
    if (req.body?.pwd) trainer.pwd = req.body.pwd;
    const result = await trainer.save();
    res.json(result);
}

const deleteTrainer = async (req, res) => {
    if (!req?.body?.id) return res.status(400).json({ 'message': 'Trainer ID required.' });

    const trainer = await Trainer.findOne({ _id: req.body.id }).exec();
    if (!trainer) {
        return res.status(204).json({ "message": `No trainer matches ID ${req.body.id}.` });
    }
    const result = await trainer.deleteOne(); //{ _id: req.body.id }
    res.json(result);
}

const getTrainer = async (req, res) => {
    if (!req?.params?.id) return res.status(400).json({ 'message': 'Trainer ID required.' });

    const trainer = await Trainer.findOne({ _id: req.params.id }).exec();
    if (!trainer) {
        return res.status(204).json({ "message": `No trainer matches ID ${req.params.id}.` });
    }
    res.json(trainer);
}

module.exports = {
    getAllTrainers,
    createNewTrainer,
    updateTrainer,
    deleteTrainer,
    getTrainer
}