import React, { useState } from 'react';
import styled from "styled-components";

export default function Searchfilter () {
    const [toggleState, setToggleState] = useState(0);
    
    const handleToggle = () => {
        setToggleState((prevState) => (prevState + 1) % 4);
        if (toggleState === 2) {
          setToggleState(0);
        }
      };

    const renderToggleMessage = () => {
        switch (toggleState) {
          case 0:
            return '제목';
          case 1:
            return '작성자';
          case 2:
            return '태그';
          default:
            return '';
    }
    };

    const Button = styled.button`
        background-color: white;
        color: #43337D;
        border-radius: 8px;
        display: flex;
        align-items: center;
        font-weight: bold;
        height: 30px;
        width: 60px;
        justify-content: center;
        padding: 0 8px;
    `;

      return (
        <Button onClick={handleToggle}>{renderToggleMessage()}</Button>
      )
}