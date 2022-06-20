import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IContentStoreInternal } from 'app/shared/model/content-store-internal.model';
import { getEntity, updateEntity, createEntity, reset } from './content-store-internal.reducer';

export const ContentStoreInternalUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const contentStoreInternalEntity = useAppSelector(state => state.contentStoreInternal.entity);
  const loading = useAppSelector(state => state.contentStoreInternal.loading);
  const updating = useAppSelector(state => state.contentStoreInternal.updating);
  const updateSuccess = useAppSelector(state => state.contentStoreInternal.updateSuccess);
  const handleClose = () => {
    props.history.push('/content-store-internal' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...contentStoreInternalEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...contentStoreInternalEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="archiscapeCoreApp.contentStoreInternal.home.createOrEditLabel" data-cy="ContentStoreInternalCreateUpdateHeading">
            <Translate contentKey="archiscapeCoreApp.contentStoreInternal.home.createOrEditLabel">
              Create or edit a ContentStoreInternal
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="content-store-internal-id"
                  label={translate('archiscapeCoreApp.contentStoreInternal.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('archiscapeCoreApp.contentStoreInternal.version')}
                id="content-store-internal-version"
                name="version"
                data-cy="version"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('archiscapeCoreApp.contentStoreInternal.insertDate')}
                id="content-store-internal-insertDate"
                name="insertDate"
                data-cy="insertDate"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('archiscapeCoreApp.contentStoreInternal.fileName')}
                id="content-store-internal-fileName"
                name="fileName"
                data-cy="fileName"
                type="text"
              />
              <ValidatedField
                label={translate('archiscapeCoreApp.contentStoreInternal.content')}
                id="content-store-internal-content"
                name="content"
                data-cy="content"
                type="textarea"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/content-store-internal" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ContentStoreInternalUpdate;
