// Get the modal
var invoiceIds = [];
var modal1 = document.getElementById("deleteRecordModal");
var deleteButton = document.getElementById("finallyDeleteRecords");

// Get the button that opens the modal
var btn = document.getElementById("deleteRecord");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close1")[0];

// When the user clicks the button, open the modal
btn.onclick = function () {
  var table = document.getElementById("TestTable");

  for (let i in table.rows) {
    let row = table.rows[i];
    if (row.cells) {
      checkboxColumn = row.cells[0];
      if (checkboxColumn.children[0].checked) {
        invoiceIds.push(row.cells[3].innerHTML);
      }
    }
  }
  console.log(invoiceIds);
  modal1.style.display = "block";
};

// When the user clicks on <span> (x), close the modal
span.onclick = function () {
  modal1.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
  if (event.target == modal) {
    modal1.style.display = "none";
  }
};

deleteButton.onclick = function () {
  let xhr = new XMLHttpRequest();
  invoiceIds.forEach((element) => {
    xhr.open(
      "GET",
      "http://localhost:8080/H2HBABBA2966/deleteinvoice?invoice_id=" + element,
      false
    );
    xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
    xhr.onreadystatechange = function () {
      console.log(xhr.responseText);
    };
    xhr.send();
  });

  window.location.reload();
};
