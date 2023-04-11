import React, { useState, useEffect } from 'react';

const url = 'http://localhost:8082';

function OwnerPage() {
  const [menuItems, setMenuItems] = useState([]);
  const [newFoodName, setNewFoodName] = useState('');
  const [newPrice, setNewPrice] = useState('');

  // Fetch menu list from local server on component mount
  useEffect(() => {
    fetchMenuList();
  }, []);

  // Function to fetch menu list from local server
  const fetchMenuList = async () => {
    try {
      const response = await fetch(`${url}/getMenu`);
      const data = await response.json();
      setMenuItems(data);
    } catch (error) {
      console.error(error);
    }
  };

  // Function to create a new menu item on local server
  const handleAddMenuItem = async (e) => {
    e.preventDefault();
    try {
      const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ foodName: newFoodName, price: newPrice }),
      };
      const response = await fetch(`${url}/addMenu`, requestOptions);
      const data = await response.json();
      if (response.ok) {
        // Show success alert
        alert('Menu item created successfully!');
        // Fetch updated menu list
        fetchMenuList();
      } else {
        // Show error alert
        alert('Failed to create menu item.');
      }
    } catch (error) {
      console.error(error);
    }
  };

  // Function to delete a menu item on local server
const handleDeleteMenuItem = async (itemId, foodName) => {
  try {
    const requestOptions = {
      method: 'DELETE',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ foodName: foodName }),
    };
    const response = await fetch(`${url}/deleteMenu/${foodName}`, requestOptions);
    if (response.ok) {
      // Show success alert
      alert('Menu item deleted successfully!');
      // Fetch updated menu list
      fetchMenuList();
    } else {
      // Show error alert
      alert('Failed to delete menu item.');
    }
  } catch (error) {
    console.error(error);
  }
};

  return (
    <>
      <h1>Owner Page</h1>
      <form onSubmit={handleAddMenuItem}>
        <label>
          FoodName:
          <input type="text" value={newFoodName} onChange={(e) => setNewFoodName(e.target.value)} />
        </label>
        <label>
          Price:
          <input type="number" value={newPrice} onChange={(e) => setNewPrice(e.target.value)} />
        </label>
        <button type="submit">Add Menu Item</button>
      </form>
      <ul>
        {menuItems.map((menuItem) => (
          <li key={menuItem.id}>
            {menuItem.foodName} - {menuItem.price}
            <button onClick={() => handleDeleteMenuItem(menuItem.id, menuItem.foodName)}>Delete</button>
          </li>
        ))}
      </ul>
    </>
  );
}

export default OwnerPage;
