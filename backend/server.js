const express = require('express');
const multer = require('multer');
const fs = require('fs');
const cors = require('cors');


const app = express();
const upload = multer({ dest: 'uploads/' });

app.use(cors());
app.post('/upload', upload.array('files', 10), (req, res) => {
    const files =req.files;
    const uploadPromises = files.map((file) => {
        const originalName = file.originalname;
        const fileName = Date.now() + '-' + originalName;

        const filePath = file.path;
        const targetPath = 'uploads/' + fileName;

        return new Promise((resolve, reject) => {
            fs.rename(filePath, targetPath, (err) => {
                if (err) {
                console.error('Fehler beim Speichern der Datei: ', err);
                reject(err);
                } else {
                    console.log('Datei erfolgreich gespeichert.', fileName);
                    resolve(fileName);
                }
        });
        });
        });
        Promise.all(uploadPromises)
            .then((fileNames) => {
                console.log('Alle Dateien erfolgreich hochgeladen.');
                res.json({ fileNames });
            })
            .catch((error) => {
             console.error('Fehler beim Hochladen der Dateien: ', error);
             res.status(500).send('Fehler beim Hochladen der Dateien.');
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
            console.log('Datei erfolgreich gelöscht.', fileName);
            res.send('Datei gelöscht.');
        }
    });
});

app.listen(8000, () => {
    console.log('Server läuft auf Port 8000');
});
