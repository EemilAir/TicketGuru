import { Container } from 'react-bootstrap';

const Header = ({ header }) => {
    return (
        <header>
            <Container>
                <h1>{header}</h1>
            </Container>
        </header>
    );
}

export default Header;