<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Bootstrap JavaScript Example</title>

  <!-- Bootstrap CSS -->
  <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-CTfnD1JnKUYIhSeV9xxTNQCxS30OEn6UIA8Z0PAyFh/9gK7QoANfN1D3pz58D/G7"
    crossorigin="anonymous"
  >
</head>
<body>
  <div class="container mt-5">
    <h1 class="text-center">Bootstrap JavaScript Example</h1>

    <!-- Example of a Modal -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
      Open Modal
    </button>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Modal Title</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            This is a Bootstrap modal! It uses JavaScript for interactivity.
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Example of a Tooltip -->
    <button type="button" class="btn btn-secondary mt-3" data-bs-toggle="tooltip" data-bs-placement="top" title="This is a tooltip">
      Hover to see Tooltip
    </button>
  </div>

  <!-- Bootstrap JavaScript -->
  <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-QFTx99BPSA5s7/oI7XaaRPj1DFCRt41YeU7JOMqH8ub64Z+v8ZzlvA2FezX95Emx"
    crossorigin="anonymous"
  ></script>

  <!-- Enable Tooltips (Optional JavaScript) -->
  <script>
    document.addEventListener('DOMContentLoaded', function () {
      var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
      tooltipTriggerList.forEach(function (tooltipTriggerEl) {
        new bootstrap.Tooltip(tooltipTriggerEl);
      });
    });
  </script>
</body>
</html>
