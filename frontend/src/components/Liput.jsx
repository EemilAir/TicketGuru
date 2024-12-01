import Lippu from './Lippu';

export default function Liput({ liput }) {
    return (
        <div className='container'>
            <h1 className="my-4">Liput</h1>
            <div className="row">
                {liput.map((lippu) => (
                    <div key={lippu.koodi} className="col-md-4">
                        <Lippu lippu={lippu} />
                    </div>
                ))}
            </div>
        </div>
    );
}