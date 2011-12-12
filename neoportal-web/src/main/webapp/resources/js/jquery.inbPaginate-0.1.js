// inbPaginate Plugin for jQuery
// version 0.1
// author: Arturo Vargas for INBio (www.inbio.ac.cr)
/* 
 * Based on opensearch.org specification to transform result sets into pagination
 * 
 * ** this is a work in progress so just some opensearch stuff are supported **
 */

(function( $ ){
    $.fn.inbPaginate = function( url, options ){
        var settings = $.extend({
            searchTerms: '',
            startIndex: 0,
            itemsPerPage: 10,
            totalResults: 'opensearch:totalResults', //name of the field containing the total number of items
            iterateItem: 'item', //the name of the xml element you need to iterate
            iterateFunction: null, //function to manage the output of the data,
            // the function must receive xml data and return html string
            firstControl: null, //selector|element for First control
            lastControl: null, //selector|element for Last control
            nextControl: null, //selector|element for Next control
            previousControl: null, //selector|element for Previous control
            numbersParent: null, //selector|element parent of list of pages
            actualPage: null, //selector|element to show the actual page
            totalPages: null //selector|element to show total number of pages
        }, options||{});
        
        //the function need a url for make the search
        if( url == null || url === undefined)
            return;
        
        //container is the div (hopely) where the content will be inserted
        settings.container$ = this;
        function getData(searchTerms, startIndex, itemsPerPage){
            var params = {};
            if(searchTerms != '')
                params.searchTerms= searchTerms;
            
            params.startIndex= settings.startIndex;
            params.itemsPerPage= settings.itemsPerPage;
            
            $.get(url, 
                params,
                function(data){
                    //check some variables
                    if(!settings.totalItems)
                        settings.totalItems = $(data).find(settings.totalResults).text();
                    
                    //call this function to manage the output data
                    if(settings.iterateFunction){
                        $(settings.container$).empty();
                        $(settings.container$).append(settings.iterateFunction(data));
                    }
                    else{   //default html generation, insert content to wrapper
                        //create the list element
                        var $ol = $("ol.inbPaginate-list");
                        if($ol.length == 0)
                            $ol = $("<ol class='inbPaginate-list'></ol>");

                        $ol.empty();
                        //
                        $(data).find(settings.iterateItem).each(function(){
                            var $li = $("<li class='inbPaginate-item'></li>");
                            $(this).children().each(function(){
                                $li.append($(this).text());
                            });

                            $ol.append($li);
                        });

                        $(settings.container$).append($ol);
                    }
                    
                    //refresh the number list
                    refreshNumbers(settings.numbersParent);
                },
                'xml');
        }
        
        /*
         * Refresh the page number list, current number, etc
         */
        function refreshNumbers(numbersParent){
            /*if(!numbersParent)    //not implemented yet
                return;*/
            
            var remainder = settings.startIndex % settings.itemsPerPage;
            var actualPage = (settings.startIndex - remainder) / settings.itemsPerPage;
            var maxPage = Math.ceil(settings.totalItems / settings.itemsPerPage);
            
            $(settings.actualPage).text(actualPage + 1);
            $(settings.totalPages).text(maxPage);
        }
        
        //assign function to respective buttons
        $(settings.firstControl).click(function(){
            settings.startIndex = 0;
            getData(settings.searchTerms, 0, settings.itemsPerPage);
        });
        
        $(settings.nextControl).click(function(){
            if((settings.startIndex + settings.itemsPerPage) >= settings.totalItems)
                return;
            else {
                settings.startIndex += settings.itemsPerPage;
                getData(settings.searchTerms, settings.startIndex, settings.itemsPerPage);
            }
        });
        
        $(settings.previousControl).click(function(){
            if(settings.startIndex == 0)
                return;
            else {
                settings.startIndex -= settings.itemsPerPage;
                getData(settings.searchTerms, settings.startIndex, settings.itemsPerPage);
            }
        });
    
        $(settings.lastControl).click(function(){
            settings.startIndex = settings.totalItems - (settings.totalItems % settings.itemsPerPage);
            getData(settings.searchTerms, settings.startIndex, settings.itemsPerPage);
        });
        
        
        getData(settings.searchTerms, settings.startIndex, settings.itemsPerPage);
        return this;
    };
})( jQuery );