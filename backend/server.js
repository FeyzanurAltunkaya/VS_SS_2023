const express = require('express');
const multer = require('multer');
const fs = require('fs');
const cors = require('cors');

const app = express();
const storage = multer.diskStorage({
    destination: 'uploads/',
    filename: function(req, file, cb) {
        cb(null, file.originalname);
    }
});
const upload = multer({ storage: storage });
app.use(cors());
app.use(express.json()); // Hinzugefügt, um JSON-Anfragen zu verarbeiten

app.post('/upload', upload.array('files', 10), (req, res) => {
    // Code zum Hochladen der Dateien
    const files = req.files;
    if (!files) {
        res.status(400).send('Es wurden keine Dateien hochgeladen.');
        return;
    }

    // Überprüfen, ob ein Array von Dateien hochgeladen wurde
    if (Array.isArray(files)) {
        const fileNames = files.map((file) => file.filename);
        res.send('Dateien erfolgreich hochgeladen: ' + fileNames.join(', '));
    } else {
        res.status(400).send('Es wurde keine Datei hochgeladen.');
    }
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
            console.log('Datei erfolgreich gelöscht:', fileName);
            res.send('Datei gelöscht.');
        }
    });
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
app.delete('/uploads/:folderName', (req, res) => {
    const folderName = req.params.folderName;
    const folderPath = 'uploads/' + folderName;

    fs.rmdir(folderPath, { recursive: true }, (err) => {
        if (err) {
            console.error('Fehler beim Löschen des Ordners: ', err);
            res.status(500).send('Fehler beim Löschen des Ordners.');
        } else {
            console.log('Ordner erfolgreich gelöscht:', folderName);
            res.send('Ordner gelöscht.');
        }
    });
});


app.listen(8000, () => {
    console.log('Server läuft auf Port 8000');
});
