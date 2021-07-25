// Get the modal
var modal2 = document.getElementById("editModal");
var editButton = document.getElementById("finallyEditRecord");
editButton.disabled = true;
function checkInvoiceAmount() {
  if ($("#editinvoiceamount").val() != " ") {
    console.log("not empty");
    $("#finallyEditRecord").attr("disabled", false);
  } else {
    $("#finallyEditRecord").attr("disabled", true);
  }
}

function resetValue() {
  document.getElementById("editinvoiceamount").value = " ";
  document.getElementById("editedNotes").value = " ";
  editButton.disabled = true;
}

var invoiceIds1 = [];
// Get the button that opens the modal
var btn = document.getElementById("edit");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close2")[0];

// When the user clicks the button, open the modal
btn.onclick = function () {
  var table = document.getElementById("TestTable");
  for (let i in table.rows) {
    let row = table.rows[i];
    if (row.cells) {
      checkboxColumn = row.cells[0];
      if (checkboxColumn.children[0].checked) {
        invoiceIds1.push(row.cells[3].innerHTML);
      }
    }
  }
  modal2.style.display = "block";
};

// When the user clicks on <span> (x), close the modal
span.onclick = function () {
  modal2.style.display = "none";
};

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
  if (event.target == modal) {
    modal2.style.display = "none";
  }
};

editButton.onclick = function () {
  let xhr = new XMLHttpRequest();
  xhr.open(
    "GET",
    "http://localhost:8080/H2HBABBA2966/editinvoice?invoice_id=" +
      invoiceIds1[0] +
      "&amount=" +
      document.getElementById("editinvoiceamount").value +
      "&notes=" +
      document.getElementById("editedNotes").value,

    false
  );
  xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
  xhr.onreadystatechange = function () {
    console.log(xhr.responseText);
  };
  xhr.send();

  window.location.reload();
};
