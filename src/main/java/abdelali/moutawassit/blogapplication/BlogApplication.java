package abdelali.moutawassit.blogapplication;

import abdelali.moutawassit.blogapplication.model.*;
import abdelali.moutawassit.blogapplication.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.*;

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
                                PostReactionRepository postReactionRepository,
                                SavedPostRepository savedPostRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                List<User> users = List.of(
                        User.builder().fullName("Alice Johnson").username("Alice Johnson").email("alice@example.com").password("pass123").bio("Dév web").profileImageUrl("https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8dXNlciUyMHByb2ZpbGV8ZW58MHx8MHx8fDA%3D").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("Bob Smith").username("Bob Smith").email("bob@example.com").password("pass123").bio("Photographe").profileImageUrl("https://images.unsplash.com/photo-1584999734482-0361aecad844?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTl8fHByb2ZpbGUlMjBwaG90b3xlbnwwfHwwfHx8MA%3D%3D").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("Charlie Lee").username("Charlie Lee").email("charlie@example.com").password("pass123").bio("Étudiant").profileImageUrl("https://img.freepik.com/premium-photo/male-russian-model-face-cosmetology-advertising_1198440-111.jpg").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("Diana Prince").username("Diana Prince").email("diana@example.com").password("pass123").bio("Superhéroïne").profileImageUrl("https://img.freepik.com/premium-photo/happy-young-students-studying-college-library-with-stack-books_21730-4486.jpg?semt=ais_hybrid&w=740").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("Elon Musk").username("Elon Musk").email("elon@example.com").password("pass123").bio("Visionnaire").profileImageUrl("https://img.freepik.com/free-photo/portrait-handsome-student-smiling_23-2148586538.jpg?semt=ais_hybrid&w=740").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("Fatima Zahra").username("Fatima Zahra").email("fatima@example.com").password("pass123").bio("Dév mobile").profileImageUrl("https://img.freepik.com/premium-photo/diverse-group-teenagers-shoot_53876-38892.jpg?w=360").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("George Lucas").username("George Lucas").email("george@example.com").password("pass123").bio("Réalisateur").profileImageUrl("https://img.freepik.com/premium-photo/young-beautiful-businesswoman-against-white-wall_251136-55504.jpg?semt=ais_hybrid&w=740").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("Hassan Rami").username("Hassan Rami").email("hassan@example.com").password("pass123").bio("UX/UI Designer").profileImageUrl("https://img.freepik.com/photos-premium/portrait-jeune-homme-intelligent-souriant-heureux-lunettes_274222-6633.jpg?w=360").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("Ismail Ait").username("Ismail Ait").email("ismail@example.com").password("pass123").bio("Freelancer").profileImageUrl("https://img.freepik.com/photos-premium/portrait-jeune-bel-homme-lunettes_127089-1348.jpg?w=360").createdAt(LocalDateTime.now()).build(),
                        User.builder().fullName("John Doe").username("John Doe").email("john@example.com").password("pass123").bio("Dév Java").profileImageUrl("https://static.vecteezy.com/ti/photos-gratuite/p1/3152807-portrait-de-jeune-homme-a-lunettes-sur-fond-noir-gratuit-photo.jpg").createdAt(LocalDateTime.now()).build()
                );
                userRepository.saveAll(users);
                System.out.println("✅ 10 utilisateurs enregistrés avec succès !");


                List<User> allUsers = userRepository.findAll();

                if (postRepository.count() == 0) {
                    for (User user : allUsers) {
                        Post post1 = Post.builder()
                                .user(user)
                                .content("Ceci est un post de " + user.getUsername() + ".")
                                .imageUrl("https://media.istockphoto.com/id/527343577/photo/looking-for-inspiration.jpg?s=612x612&w=0&k=20&c=ck3lswRHkRpvxurShBPaRj5dvieSo1N0ZPqXA4XdOnk=")
                                .videoUrl(null)
                                .createdAt(LocalDateTime.now())
                                .likeCount(0)
                                .shareCount(0)
                                .build();

                        Post post2 = Post.builder()
                                .user(user)
                                .content("Un autre post intéressant par " + user.getUsername() + ".")
                                .imageUrl("https://media.istockphoto.com/id/876637476/photo/business-man-working-on-documents-looking-concentrated.jpg?s=612x612&w=0&k=20&c=pJVPO1f2b49gLKu0USXoRHURSD2xSclCmN0RCt2g6oo=")
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

                    // Enregistrer des posts sauvegardés
                    List<Post> allPostss = postRepository.findAll();
                    List<User> allUserss = userRepository.findAll();
                    List<SavedPost> savedPosts = new ArrayList<>();

                    for (User user : allUsers) {
                        // Chaque utilisateur sauvegarde entre 1 et 3 posts aléatoires (différents des siens)
                        Collections.shuffle(allPosts);
                        int savedCount = 1 + random.nextInt(3);

                        int count = 0;
                        for (Post post : allPosts) {
                            if (!post.getUser().getId().equals(user.getId()) && count < savedCount) {
                                SavedPost savedPost = SavedPost.builder()
                                        .user(user)
                                        .post(post)
                                        .savedAt(LocalDateTime.now())
                                        .build();
                                savedPosts.add(savedPost);
                                count++;
                            }
                        }
                    }

                    savedPostRepository.saveAll(savedPosts);
                    System.out.println("✅ SavedPosts enregistrés : " + savedPosts.size());

                }
            }
        };
    }
}
