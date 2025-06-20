import { PropTypes } from "prop-types"

export const ProductDetail = ({ handlerRemove, handlerProductSelected,  product }) => {

    return (
        <>
            <tr>
                <td>{product.name}</td>
                <td>{product.price}</td>
                <td>{product.description}</td>
                <td>
                    <button className="btn btn-secondary btn-sm" onClick={() => handlerProductSelected(product)}>
                        update
                    </button>
                </td>
                <td>
                    <button className="btn btn-danger btn-sm" onClick={() => handlerRemove(product.id)}>
                        remove
                    </button>
                </td>
            </tr>
        </>
    )
}

ProductDetail.propTypes = {
    product: PropTypes.object.isRequired
}