import { PropTypes } from "prop-types"
import { ProductDetail } from "./ProductDetail"

export const ProductGrid = ({ handlerRemove, handlerProductSelected, products = [] }) => {
    return (
        <>
            <table className="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>name</th>
                        <th>description</th>
                        <th>price</th>
                        <th>update</th>
                        <th>remove</th>
                    </tr>
                </thead>
                <tbody>
                    {products.map(product => {
                        return <ProductDetail handlerRemove={handlerRemove} handlerProductSelected={handlerProductSelected} product={product} key={product.id} />
                    })}
                </tbody>
            </table>
        </>
    )
}

ProductGrid.propTypes = {
    products: PropTypes.array.isRequired,
    handlerRemove: PropTypes.object.isRequired
}