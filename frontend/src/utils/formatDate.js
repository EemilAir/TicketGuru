import dayjs from 'dayjs';
import 'dayjs/locale/fi';

dayjs.locale('fi'); // Asetetaan dayjs käyttämään suomenkielistä localea

export const formatDate = (isoString) => {
    return dayjs(isoString).format('dddd, D.M.YYYY HH:mm');
};