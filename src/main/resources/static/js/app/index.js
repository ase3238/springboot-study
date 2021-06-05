const init = () => {
    const postFunction = {
        save: () => {
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
        },
        update: () => {
            const key = ['title', 'content'];
            const data = key.reduce((res, key) => ({
                ...res,
                [key]: $(`#${key}`).val(),
            }), {});
            const id = $('#id').val();

            $.ajax({
                type: 'PUT',
                url: `/api/v1/posts/${id}`,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(() => {
                alert('글이 수정되었습니다.');
                window.location.href = '/';
            }).fail((error) => {
                alert(JSON.stringify(error));
            });
        },
        delete: () => {
            const id = $('#id').val();
            $.ajax({
                type: 'DELETE',
                url: `/api/v1/posts/${id}`,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
            }).done(() => {
                alert('글이 삭제되었습니다.');
                window.location.href = '/';
            }).fail((error) => {
                alert(JSON.stringify(error));
            });
        },
    }
    $('#btn-save').on('click', () => {
        postFunction.save();
    });
    $('#btn-update').on('click', () => {
        postFunction.update();
    });
    $('#btn-delete').on('click', () => {
        postFunction.delete();
    });
};

init();