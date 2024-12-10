import { formatDate } from './formatDate';
import { generateQRCode } from './generateQRCode';

export const getLippuPrintHtml = (lippu) => {
    return `
        <html>
        <head>
            <title>Tulosta Lippu</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            h1, h2 {
                color: #333;
            }
            .lippu {
                border: 3px solid #ddd;
                padding: 10px;
                margin-bottom: 10px;
                border-radius: 5px;
                background-color: #fff;
                page-break-inside: avoid;

            }
            .lippu img {
                display: block;
                margin-top: 10px;
                width: 250px; /* Suurenna QR-koodin leveys */
            }
        </style>
        </head>
        <body>
            <div class="lippu">
                <h2>${lippu.lipputyyppi}</h2>
                <p><strong>Lipun koodi:</strong> ${lippu.koodi}</p>
                <p><strong>Tapahtuma ID:</strong> ${lippu.tapahtumaId}</p>
                <p><strong>Tila:</strong> ${lippu.tila === 0 ? 'Käytetty' : 'Käyttämätön'}</p>
                <p><strong>Käyttöaika:</strong> ${lippu.kayttoaika ? formatDate(lippu.kayttoaika) : '-'}</p>
                <img src='${generateQRCode(lippu)}' alt='QR-koodi' />
            </div>
        </body>
        </html>
    `;
}