<h1>README for Zynetic Assignment Repository</h1>

  <h2>Setup Instructions</h2>

  <h3>1. Clone the Repository</h3>
  <p>Clone the repository to your local machine using the following command:</p>
  <pre><code>git clone https://github.com/kumarwhocodes/zynetic_assignment.git</code></pre>
  <p><em>But, preferably download the zip file from Google Forms.</em></p>

  <h3>2. Navigate to the Project Directory</h3>
  <p>Move into the cloned directory:</p>
  <pre><code>cd zynetic_assignment</code></pre>

  <h3>3. Build the Project</h3>
  <p>The project uses Gradle for building. Run the following command to build the project:</p>
  <pre><code>./gradlew build</code></pre>

  <h3>4. Run the Application</h3>
  <p>Start the application using:</p>
  <pre><code>./gradlew bootRun</code></pre>

  <h3>5. Docker Setup (Optional)</h3>
  <p>If you prefer to run the application in a Docker container:</p>
  <ul>
    <li>Build the Docker image:
      <pre><code>docker build -t zynetic_assignment .</code></pre>
    </li>
    <li>Run the Docker container:
      <pre><code>docker run -p 8080:8080 zynetic_assignment</code></pre>
    </li>
  </ul>

  <h2>API Endpoints and Sample Requests</h2>
  <p>Refer to the documentation section for full details on available API endpoints and example usage.</p>

  <h1>Bookstore API Documentation</h1>

  <h2>1. GET /api/auth/signup</h2>
  <p><strong>Description:</strong> Registers the user and returns a token.</p>
  <p><strong>Request:</strong></p>
  <pre><code>curl -X GET http://localhost:8080/api/auth/signup</code></pre>
  <p><strong>Response:</strong></p>
  <pre><code>{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}</code></pre>

  <h2>2. GET /api/books/{id}</h2>
  <p><strong>Description:</strong> Retrieves book details by ID.</p>
  <pre><code>curl -X GET http://localhost:8080/api/books/1</code></pre>
  <pre><code>{
  "id": 1,
  "title": "Sample Book",
  "author": "John Doe",
  "category": "Fiction",
  "price": 199,
  "rating": 5,
  "publishedDate": "2025-04-08"
}</code></pre>

  <h2>3. PUT /api/books/{id}</h2>
  <p><strong>Description:</strong> Updates book information by ID.</p>
  <pre><code>curl -X PUT http://localhost:8080/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Updated Book",
    "author": "Jane Doe",
    "category": "Drama",
    "price": 249,
    "rating": 4,
    "publishedDate": "2025-04-09"
  }'</code></pre>
  <pre><code>{
  "message": "Book updated successfully"
}</code></pre>

  <h2>4. DELETE /api/books/{id}</h2>
  <p><strong>Description:</strong> Deletes a book by ID.</p>
  <pre><code>curl -X DELETE http://localhost:8080/api/books/1</code></pre>
  <pre><code>{
  "message": "Book deleted successfully"
}</code></pre>

  <h2>5. POST /api/books/create</h2>
  <p><strong>Description:</strong> Creates a new book.</p>
  <pre><code>curl -X POST http://localhost:8080/api/books/create \
  -H "Content-Type: application/json" \
  -d '{
    "title": "New Book",
    "author": "Author Name",
    "category": "Sci-Fi",
    "price": 150,
    "rating": 5,
    "publishedDate": "2025-04-08"
  }'</code></pre>
  <pre><code>{
  "message": "Book created successfully",
  "id": 2
}</code></pre>

  <h2>6. GET /api/books/sort</h2>
  <p><strong>Description:</strong> Returns books sorted by criteria.</p>
  <pre><code>curl -X GET "http://localhost:8080/api/books/sort?by=price&order=asc"</code></pre>
  <pre><code>[
  { "id": 3, "title": "Affordable Book", "price": 100 },
  { "id": 4, "title": "Premium Book", "price": 300 }
]</code></pre>

  <h2>7. GET /api/books/search</h2>
  <p><strong>Description:</strong> Search books by partial title match.</p>
  <pre><code>curl -X GET "http://localhost:8080/api/books/search?title=sample"</code></pre>
  <pre><code>[
  { "id": 1, "title": "Sample Book", "author": "John Doe" }
]</code></pre>

  <h2>8. GET /api/books/paged</h2>
  <p><strong>Description:</strong> Returns paginated list of books.</p>
  <pre><code>curl -X GET "http://localhost:8080/api/books/paged?page=0&size=10"</code></pre>
  <pre><code>{
  "content": [
    { "id": 1, "title": "Book 1" },
    { "id": 2, "title": "Book 2" }
  ],
  "totalPages": 5,
  "totalElements": 50
}</code></pre>

  <h2>9. GET /api/books/get-all</h2>
  <p><strong>Description:</strong> Returns all books without pagination.</p>
  <pre><code>curl -X GET http://localhost:8080/api/books/get-all</code></pre>
  <pre><code>[
  { "id": 1, "title": "Book 1" },
  { "id": 2, "title": "Book 2" }
]</code></pre>

  <h2>10. GET /api/books/filter</h2>
  <p><strong>Description:</strong> Filters books by author, category, or rating.</p>
  <pre><code>curl -X GET "http://localhost:8080/api/books/filter?author=Jane&category=Drama&rating=4"</code></pre>
  <pre><code>[
  {
    "id": 5,
    "title": "Drama Queen",
    "author": "Jane",
    "category": "Drama",
    "rating": 4
  }
]</code></pre>

</body>
</html>
