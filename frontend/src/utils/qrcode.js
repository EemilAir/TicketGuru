import QRCode from 'qrcode-generator';

export function generateQRCode(lippu) {
    const baseUrl = 'http://localhost:8080/api/liput'; // URL-osoite, joka laukaisee lipun tilan päivityksen
    const qrData = `${baseUrl}/${lippu.koodi}?tila=${lippu.tila === 0 ? 1 : 0}`; // Päivitetään tila käytetyksi tai käyttämättömäksi
    const qr = QRCode(0, 'L'); // 0 = version auto, 'L' = Low error correction
    qr.addData(qrData);
    qr.make();
    console.log(qrData);
    return qr.createDataURL(); // Palauttaa QR-koodin Data URL -muodossa
}