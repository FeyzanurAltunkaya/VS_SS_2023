const express = require('express');
const multer = require('multer');
const fs = require('fs');
const cors = require('cors');

const app = express();
const upload = multer({ dest: 'uploads/' });

app.use(cors());
app.use(express.json()); // Hinzugefügt, um JSON-Anfragen zu verarbeiten

app.post('/upload', upload.array('files', 10), (req, res) => {
    // Code zum Hochladen der Dateien
    // ...
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
    // Code zum Löschen einer Datei
    // ...
});

app.get('/folders', (req, res) => {
    fs.readdir('uploads/', { withFileTypes: true }, (err, files) => {
        if (err) {
            console.error('Fehler beim Lesen der Ordner: ', err);
            res.status(500).send('Fehler beim Lesen der Ordner.');
        } else {
            const folders = files.filter((file) => file.isDirectory()).map((folder) => folder.name);
            res.json({ folders });
        }
    });
});

app.post('/create-folder', (req, res) => {
    const folderName = req.body.folderName;
    const folderPath = 'uploads/' + folderName;

    fs.mkdir(folderPath, { recursive: true }, (err) => {
        if (err) {
            console.error('Fehler beim Erstellen des Ordners: ', err);
            res.status(500).send('Fehler beim Erstellen des Ordners.');
        } else {
            console.log('Ordner erfolgreich erstellt.', folderName);
            res.send('Ordner erstellt.');
        }
    });
});

app.listen(8000, () => {
    console.log('Server läuft auf Port 8000');
});
