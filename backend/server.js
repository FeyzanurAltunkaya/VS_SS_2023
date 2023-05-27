const express = require('express');
const multer = require('multer');
const fs = require('fs');
const cors = require('cors');


const app = express();
const upload = multer({ dest: 'uploads/' });

app.use(cors());
app.post('/upload', upload.single('file'), (req, res) => {
    const originalName = req.file.originalname;
    const fileName = Date.now() + '-' + originalName;
    //auf path achten
    const filePath = req.file.path;
    const targetPath = 'uploads/' + fileName;

    fs.rename(filePath, targetPath, (err) => {
        if (err) {
            console.error('Fehler beim Speichern der Datei: ', err);
            res.status(500).send('Fehler beim Speichern der Datei.');
        } else {
            console.log('Datei erfolgreich gespeichert.');
            res.send('Datei hochgeladen und gespeichert.');
        }
    });
});

app.get('/files', (req, res) => {
    fs.readdir('uploads/', (err, files) => {
        if (err) {
            console.error('Fehler beim Lesen der Dateien: ', err);
            res.status(500).send('Fehler beim Lesen der Dateien.');
        } else {
            res.json({ files });
        }
    });
});

app.delete('/delete/:fileName', (req, res) => {
    const fileName = req.params.fileName;
    const filePath = 'uploads/' + fileName;

    fs.unlink(filePath, (err) => {
        if (err) {
            console.error('Fehler beim Löschen der Datei: ', err);
            res.status(500).send('Fehler beim Löschen der Datei.');
        } else {
            console.log('Datei erfolgreich gelöscht.');
            res.send('Datei gelöscht.');
        }
    });
});

app.listen(8000, () => {
    console.log('Server läuft auf Port 8000');
});
