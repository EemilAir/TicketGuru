import { formatDate } from './formatDate';

export const getMyyntitapahtumaPrintHtml = (myyntitapahtuma) => {
    return `
        <html>
        <head>
            <title>Tulosta myyntitapahtuma</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            h1, h2 {
                color: #333;
            }
            .myyntitapahtuma {
                border: 1px solid #ddd;
                padding: 10px;
                margin-bottom: 10px;
                border-radius: 5px;
                background-color: #fff;
                width: 200px;
                height: 300px;
            }
            .lippu {
                border: 1px solid #ddd;
                padding: 10px;
                margin-bottom: 10px;
                border-radius: 5px;
                background-color: #fff;
                width: 200px;
                height: 300px;
            }
            .lippu img {
                display: block;
                margin-top: 10px;
                width: 150px; 
            }
        </style>
        </head>
        <body>
            <div class="myyntitapahtuma">
                <h1>Myyntitapahtuma: ${myyntitapahtuma.myyntitapahtumaId}</h1>
                <p><strong>Summa:</strong> ${myyntitapahtuma.summa} €</p>
                <p><strong>Maksutapa:</strong> ${myyntitapahtuma.maksutapa}</p>
                <p><strong>Maksupäivämäärä:</strong> ${formatDate(myyntitapahtuma.maksupvm)}</p>
                <p><strong>Käyttäjä ID:</strong> ${myyntitapahtuma.kayttajaId}</p>
                <p><strong>Tapahtuma ID:</strong> ${myyntitapahtuma.tapahtumaId}</p>
                <div>
                    <h2>Liput</h2>
                    ${myyntitapahtuma.liput.map(lippu => `
                        <div class="lippu">
                            <h3>${lippu.lipputyyppi}</h3>
                            <p><strong>Lippu ID:</strong> ${lippu.koodi}</p>
                            <p><strong>Tapahtuma ID:</strong> ${lippu.tapahtumaId}</p>
                            <img src="${lippu.qrCodeUrl}" alt="QR Code" />
                        </div>
                    `).join('')}
                </div>
            </div>
        </body>
        </html>
    `;
};