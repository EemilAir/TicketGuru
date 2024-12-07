import Header from "./Header";
import Navigation from "./Navigation";
import { Container } from 'react-bootstrap';

const PrivateLayout = ({ children, header }) => {
    return (
        <div>
            <Navigation />
            <Container>
                <Header header={header} />
                <main>
                    {children}
                </main>
            </Container>
        </div>
    );
}

export default PrivateLayout;