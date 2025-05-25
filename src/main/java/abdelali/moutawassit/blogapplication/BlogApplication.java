package abdelali.moutawassit.blogapplication;

import abdelali.moutawassit.blogapplication.model.*;
import abdelali.moutawassit.blogapplication.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository,
                                PostRepository postRepository,
                                CommentRepository commentRepository,
                                CommentReactionRepository commentReactionRepository,
                                PostReactionRepository postReactionRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                List<User> users = List.of(
                        User.builder().fullName("Alice Johnson").username("alicej").email("alice@example.com").password("pass123").bio("Dév web").profileImageUrl("url1").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("Bob Smith").username("bobsmith").email("bob@example.com").password("pass123").bio("Photographe").profileImageUrl("url2").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("Charlie Lee").username("charlie").email("charlie@example.com").password("pass123").bio("Étudiant").profileImageUrl("url3").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("Diana Prince").username("diana").email("diana@example.com").password("pass123").bio("Superhéroïne").profileImageUrl("url4").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("Elon Musk").username("elonm").email("elon@example.com").password("pass123").bio("Visionnaire").profileImageUrl("url5").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("Fatima Zahra").username("fatimaz").email("fatima@example.com").password("pass123").bio("Dév mobile").profileImageUrl("url6").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("George Lucas").username("glucas").email("george@example.com").password("pass123").bio("Réalisateur").profileImageUrl("url7").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("Hassan Rami").username("hassr").email("hassan@example.com").password("pass123").bio("UX/UI Designer").profileImageUrl("url8").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("Ismail Ait").username("ismaila").email("ismail@example.com").password("pass123").bio("Freelancer").profileImageUrl("url9").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("John Doe").username("johnd").email("john@example.com").password("pass123").bio("Dév Java").profileImageUrl("url10").createdAt(LocalDateTime.now()).build()
                );
                userRepository.saveAll(users);
                System.out.println("✅ 10 utilisateurs enregistrés avec succès !");

                List<User> allUsers = userRepository.findAll();

                if (postRepository.count() == 0) {
                    for (User user : allUsers) {
                        Post post1 = Post.builder()
                                .user(user)
                                .content("Ceci est un post de " + user.getUsername() + ".")
                                .imageUrl(null)
                                .videoUrl(null)
                                .createdAt(LocalDateTime.now())
                                .likeCount(0)
                                .shareCount(0)
                                .build();

                        Post post2 = Post.builder()
                                .user(user)
                                .content("Un autre post intéressant par " + user.getUsername() + ".")
                                .imageUrl("https://example.com/image.png")
                                .createdAt(LocalDateTime.now())
                                .likeCount(0)
                                .shareCount(0)
                                .build();

                        postRepository.save(post1);
                        postRepository.save(post2);
                    }
                    System.out.println("✅ Posts enregistrés avec succès !");

                    // Enregistrer des commentaires
                    List<Post> allPosts = postRepository.findAll();
                    List<Comment> comments = new ArrayList<>();

                    for (Post post : allPosts) {
                        for (User commenter : allUsers) {
                            Comment comment = Comment.builder()
                                    .content("Commentaire de " + commenter.getUsername() + " sur le post de " + post.getUser().getUsername())
                                    .createdAt(LocalDateTime.now())
                                    .user(commenter)
                                    .post(post)
                                    .likeCount(0)
                                    .build();
                            comments.add(comment);
                        }
                    }

                    commentRepository.saveAll(comments);
                    System.out.println("✅ Commentaires enregistrés : " + comments.size());

                    // Ajouter des réactions aux commentaires
                    List<CommentReaction> reactions = new ArrayList<>();
                    Random random = new Random();
                    CommentReaction.ReactionType[] types = CommentReaction.ReactionType.values();

                    for (Comment comment : comments) {
                        // Chaque commentaire reçoit 1 à 3 réactions de différents utilisateurs
                        Collections.shuffle(allUsers);
                        int reactionCount = 1 + random.nextInt(3);
                        for (int i = 0; i < reactionCount; i++) {
                            User reactingUser = allUsers.get(i);
                            CommentReaction reaction = CommentReaction.builder()
                                    .comment(comment)
                                    .user(reactingUser)
                                    .type(types[random.nextInt(types.length)])
                                    .reactedAt(LocalDateTime.now())
                                    .build();
                            reactions.add(reaction);
                        }
                    }

                    commentReactionRepository.saveAll(reactions);
                    System.out.println("✅ Réactions enregistrées : " + reactions.size());

                    // Ajouter des réactions aux posts
                    List<PostReaction> postReactions = new ArrayList<>();
                    PostReaction.ReactionType[] postTypes = PostReaction.ReactionType.values();

                    for (Post post : postRepository.findAll()) {
                        // Chaque post reçoit 1 à 3 réactions de différents utilisateurs
                        Collections.shuffle(allUsers);
                        int reactionCount = 1 + random.nextInt(3); // entre 1 et 3 réactions
                        for (int i = 0; i < reactionCount; i++) {
                            User reactingUser = allUsers.get(i);

                            // Vérifie que l'utilisateur ne réagit pas à son propre post
                            if (!reactingUser.getId().equals(post.getUser().getId())) {
                                PostReaction postReaction = PostReaction.builder()
                                        .post(post)
                                        .user(reactingUser)
                                        .type(postTypes[random.nextInt(postTypes.length)])
                                        .reactedAt(LocalDateTime.now())
                                        .build();
                                postReactions.add(postReaction);
                            }
                        }
                    }

                    postReactionRepository.saveAll(postReactions);
                    System.out.println("✅ Réactions aux posts enregistrées : " + postReactions.size());

                }
            }
        };
    }
}
