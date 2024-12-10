import QRCode from 'qrcode-generator';
const baseUrl = import.meta.env.VITE_APP_DEV_API_URL + '/api/liput'; // URL-osoite, joka laukaisee lipun tilan päivityksen

export function generateQRCode(lippu) {
    console.log(baseUrl)
    const qrData = `${baseUrl}?koodi=${lippu.koodi}`; // Päivitetään tila käytetyksi tai käyttämättömäksi
    const qr = QRCode(0, 'L'); // 0 = version auto, 'L' = Low error correction
    qr.addData(qrData);
    qr.make();
    return qr.createDataURL(); // Palauttaa QR-koodin Data URL -muodossa
}