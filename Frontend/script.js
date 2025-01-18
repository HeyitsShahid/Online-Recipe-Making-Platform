<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Recipe Platform with JavaScript</title>
  <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-CTfnD1JnKUYIhSeV9xxTNQCxS30OEn6UIA8Z0PAyFh/9gK7QoANfN1D3pz58D/G7"
    crossorigin="anonymous"
  >
</head>
<body>
  <div class="container mt-5">
    <h1 class="text-center">Add Your Recipe</h1>
    
    <!-- Recipe Form -->
    <form id="recipeForm">
      <div class="mb-3">
        <label for="recipeName" class="form-label">Recipe Name</label>
        <input type="text" id="recipeName" class="form-control" placeholder="Enter recipe name" required>
      </div>
      <div class="mb-3">
        <label for="ingredients" class="form-label">Ingredients</label>
        <textarea id="ingredients" class="form-control" rows="3" placeholder="Enter ingredients" required></textarea>
      </div>
      <button type="submit" class="btn btn-primary">Add Recipe</button>
    </form>

    <!-- Recipes List -->
    <h2 class="mt-5">Recipes</h2>
    <ul id="recipeList" class="list-group"></ul>
  </div>

  <!-- Bootstrap JS -->
  <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-QFTx99BPSA5s7/oI7XaaRPj1DFCRt41YeU7JOMqH8ub64Z+v8ZzlvA2FezX95Emx"
    crossorigin="anonymous"
  ></script>

  <!-- JavaScript Code -->
  <script>
    document.addEventListener('DOMContentLoaded', function () {
      const recipeForm = document.getElementById('recipeForm');
      const recipeList = document.getElementById('recipeList');

      // Handle form submission
      recipeForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent form from refreshing the page

        // Get form data
        const recipeName = document.getElementById('recipeName').value;
        const ingredients = document.getElementById('ingredients').value;

        // Validate inputs
        if (recipeName.trim() === '' || ingredients.trim() === '') {
          alert('Please fill in all fields.');
          return;
        }

        // Create a new list item for the recipe
        const listItem = document.createElement('li');
        listItem.className = 'list-group-item';
        listItem.innerHTML = `
          <strong>${recipeName}</strong>
          <p>${ingredients}</p>
          <button class="btn btn-danger btn-sm delete-btn">Delete</button>
        `;

        // Add delete functionality
        listItem.querySelector('.delete-btn').addEventListener('click', function () {
          recipeList.removeChild(listItem);
        });

        // Append the recipe to the list
        recipeList.appendChild(listItem);

        // Clear form inputs
        recipeForm.reset();
      });
    });
  </script>
</body>
</html>
