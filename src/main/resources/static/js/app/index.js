const init = () => {
    const save = () => {
        const key = ['title', 'author', 'content'];
        const data = key.reduce((res, key) => ({
            ...res,
            [key]: $(`#${key}`).val(),
        }), {});

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(() => {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail((error) => {
            alert(JSON.stringify(error));
        });
    };
    $('#btn-save').on('click', () => {
        save();
    })
};

init();