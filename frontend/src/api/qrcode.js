import QRCode from 'qrcode-generator';

export function generateQRCode(value) {
    const qr = QRCode(0, 'L'); // 0 = version auto, 'L' = Low error correction
    qr.addData(value);
    qr.make();
    return qr.createDataURL(); // Palauttaa QR-koodin Data URL -muodossa
}
