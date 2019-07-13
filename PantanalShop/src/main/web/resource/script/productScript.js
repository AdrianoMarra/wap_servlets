(function(){
    "use strict";

    $(function (){
        function initialize(){
            let urlpath = window.location.href;
            let url = new URL(urlpath);
            let id = url.searchParams.get("id");
            console.log(id);
            $.get('/API/product', {"id": id}, showImage, "json");
        }

        function showImage(data) {

            let content = `
                             <div class="col-8 card">
                                <div class="row card-body">
                                    <div class="col-7">
                                        <div class="item-photo">
                                            <img id="product-img" src="${data[0].picture}" alt="${data[0].name}" />
                                        </div>
                                    </div>
                                    
                                    <div class="col-5">
                                        <h5>${data[0].name}</h5>
                                        <div class="col-xs-9">
                                            <p class="card-text">
                                                 ${data[0].description}
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-4">
                                <h6 class="price">$${data[0].price}</h6>
                                <h6 class="title-attr">Quantity:</h6>
                                <div class="btnclass">
                                    <form action="/API/product" method="post">
                                        <input type="number" min="1" value="1" name="qtd" />
                                        <input type="hidden" value="${data[0].name}" name="name" />
                                        <input type="hidden" value="${data[0].id}" name="id" />
                                        <input type="hidden" value="${data[0].price}" name="price" />
                                        <input type="hidden" value="${data[0].picture}" name="pic" />
                                        <input type="hidden" value="${data[0].description}" name="desc" />
                                        <input type="hidden" value="${data[0].type}" name="type" />
                                        <div class="section">
                                            <br />
                                            <input type="submit" class="btn btn-success" value="Add to cart">
                                        </div>
                                    </form>
                                </div>
                            </div>`;

            let div = $("<div>").innerHTML = content;
            $("#addClass").append(div);
        }

        initialize();
        setTimeout(() => $("#msg-error").slideUp(300), 1500);
    });

})();
