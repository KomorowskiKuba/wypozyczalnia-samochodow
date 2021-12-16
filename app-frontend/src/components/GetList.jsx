import React from "react";
import PropTypes from 'prop-types';

GetList.propTypes = {
    allCarsList: PropTypes.array,
};
GetList.defaultProps = {
    allCarsList: [],
}

function GetList(props) {
    const {allCarsList} = props;

    return (
        <ul className="post-list">
            {allCarsList.map(post=>(
                <li key={post.id}>{post.model}</li>
            ))}
        </ul>
    );
}

export default GetList