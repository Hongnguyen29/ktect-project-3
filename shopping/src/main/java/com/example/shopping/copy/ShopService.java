package com.example.shopping.copy;

/*@Service
@Slf4j
@RequiredArgsConstructor*/
public class ShopService {
   /* private final ItemCategoryRepository categoryRepository;
    private final ShopRepository shopRepository;
    private final OpenRequestRepository openRepository;
    private final ClosureRequestRepository closureRepository;
    private final UserRepository userRepository;

    public List<String>  listCategory(){
        return categoryRepository.findAllCategoryNames();
    }
*/
   /* public OpenRequestView openShop(
            String username,
            OpenRequestDto openDto){
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        OpenRequest openRequest1 = new OpenRequest();
    *//*    if( openDto.getShopName() != null
                && openDto.getDescription() != null
                && openDto.getCategories() != null ){
            openRequest1.setUser(user);
            openRequest1.setShopName(openDto.getShopName());
            openRequest1.setDescription(openDto.getDescription());
            openRequest1.setCategories(openDto.getCategories());
            openRequest1.setRequestStatus(RequestStatus.PENDING);
            openRequest1.setCreatedAt(LocalDateTime.now());
        }*//*
        openRequest1.setUser(user);
        openRequest1.setShopName(openDto.getShopName());
        openRequest1.setDescription(openDto.getDescription());
        openRequest1.setCategories(openDto.getCategories());
        openRequest1.setRequestStatus(RequestStatus.PENDING);
        openRequest1.setCreatedAt(LocalDateTime.now());
        openRepository.save(openRequest1);
        return fromEntity(openRequest1);
    }
    public OpenRequestView readOneRequest(
            Long requestId,
            String username1
    ){
        OpenRequest openRequest= openRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Resquest not found"));
        String username2 =  openRequest.getUser().getUsername();
        if(!(username1.equals("admin") || username1.equals(username2))){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return  OpenRequestView.fromEntity(openRequest);
    }
    public List<OpenRequestView> readAllRequest(){
        List<OpenRequest> openRequests = openRepository.findAllOrderedByStatusAndCreatedAt();
        List<OpenRequestView> openRequestViews = new ArrayList<>();
        for (OpenRequest o : openRequests){
            openRequestViews.add(OpenRequestView.fromEntity(o));
        }
        return openRequestViews;
    }*/






}
