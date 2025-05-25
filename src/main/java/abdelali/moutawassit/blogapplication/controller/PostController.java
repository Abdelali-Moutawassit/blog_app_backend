package abdelali.moutawassit.blogapplication.controller;

import abdelali.moutawassit.blogapplication.dto.PostRequestDTO;
import abdelali.moutawassit.blogapplication.dto.PostResponseDTO;
import abdelali.moutawassit.blogapplication.service.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Créer un post (POST /api/posts?userId=...)
    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO postRequest,
                                                      @RequestParam Long userId) {
        PostResponseDTO responseDTO = postService.createPost(postRequest, userId);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Récupérer un post par id (GET /api/posts/{id})
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long id) {
        PostResponseDTO responseDTO = postService.getPostById(id);
        return ResponseEntity.ok(responseDTO);
    }

    // Récupérer tous les posts (GET /api/posts)
    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
        List<PostResponseDTO> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // Mettre à jour un post (PUT /api/posts/{id})
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long id,
                                                      @RequestBody PostRequestDTO postRequest) {
        PostResponseDTO updatedPost = postService.updatePost(id, postRequest);
        return ResponseEntity.ok(updatedPost);
    }

    // Supprimer un post (DELETE /api/posts/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
