# bin/bash

#set -a
#source /workspace/advanced-cdc-for-astra/.env
#set +a

sleep=2;
file="SM-engineering-stackexchange-posts.xml";

read_dom () {
    local IFS=\>;
    read -d \< ENTITY CONTENT;
    local ret=$?;
    TAG_NAME=${ENTITY%% *};
    ATTRIBUTES=${ENTITY#* };
    return $ret;
}

parse_dom () {
    if [[ $TAG_NAME = "row" ]] ; then
        eval local $ATTRIBUTES;
            
        local t=${Tags//&gt;/,};

        echo "insert into posts(postTypeId, body, Tags) values('"$PostTypeId"', '"${Body//\'/\'\'}"', "{${t//&lt;/}}");";
            # $Id
            # $PostTypeId
            # $AcceptedAnswerId
            # $CreationDate
            # $Score
            # $ViewCount
            # $Body
            # $OwnerUserId
            # $OwnerDisplayName
            # $LastEditorUserId
            # $LastEditDate
            # $LastActivityDate
            # $Tags
            # $AnswerCount
            # $CommentCount
            # $FavoriteCount
        sleep $sleep;
    fi
}

begin_processing(){
    while read_dom; do
        parse_dom;
    done < $file
}

begin_processing;