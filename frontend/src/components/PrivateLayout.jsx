import Header from "./Header";

const PrivateLayout = ({ children, header }) => {
    return (
        <div>
            <Header header={header} />
            <main>{children}</main>
        </div>
    );
}

export default PrivateLayout;