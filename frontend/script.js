const API = "http://localhost:8080/api/notes";

document.addEventListener("DOMContentLoaded", () => {

  document.getElementById("uploadForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const fd = new FormData();
    fd.append("title", noteTitle.value);
    fd.append("subject", subject.value);
    fd.append("source", source.value);
    fd.append("studentName", studentName.value);
    fd.append("rollNo", rollNumber.value);
    fd.append("file", pdfFile.files[0]);

    await fetch(API + "/upload", {
      method: "POST",
      body: fd
    });

    loadNotes();
  });

  loadNotes();
});

/* SCROLL NAVIGATION */
function scrollToSection(id) {
  document.getElementById(id).scrollIntoView({ behavior: "smooth" });
}

/* LOAD NOTES */
async function loadNotes() {
  const res = await fetch(API);
  const data = await res.json();

  const grid = document.getElementById("notesGrid");
  const topGrid = document.getElementById("topGrid");

  grid.innerHTML = "";
  topGrid.innerHTML = "";

  const sorted = [...data].sort((a,b)=>b.averageRating-a.averageRating);

  data.forEach(n => {
    grid.innerHTML += createCard(n);
  });

  sorted.slice(0,3).forEach(n => {
    topGrid.innerHTML += createCard(n);
  });
}

/* CARD TEMPLATE */
function createCard(n) {
  let stars = "";
  for (let i=1;i<=5;i++){
    stars += `<span class="star" onclick="rate('${n.id}',${i})">★</span>`;
  }

  return `
    <div class="card">
      <h3>${n.title}</h3>
      <p>${n.subject}</p>
      <p>${n.studentName || "Unknown"}</p>
      <p>⭐ ${n.averageRating.toFixed(1)}</p>

      <a href="http://localhost:8080${n.fileUrl}" target="_blank">Open PDF</a>

      <div>${stars}</div>
    </div>
  `;
}

/* RATE */
async function rate(id,val){
  await fetch(API + "/" + id + "/rate",{
    method:"POST",
    headers:{"Content-Type":"application/json"},
    body:JSON.stringify({rating:val})
  });
  loadNotes();
}