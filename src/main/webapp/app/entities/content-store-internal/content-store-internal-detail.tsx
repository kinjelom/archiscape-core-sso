import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './content-store-internal.reducer';

export const ContentStoreInternalDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const contentStoreInternalEntity = useAppSelector(state => state.contentStoreInternal.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="contentStoreInternalDetailsHeading">
          <Translate contentKey="archiscapeCoreApp.contentStoreInternal.detail.title">ContentStoreInternal</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="archiscapeCoreApp.contentStoreInternal.id">Id</Translate>
            </span>
          </dt>
          <dd>{contentStoreInternalEntity.id}</dd>
          <dt>
            <span id="version">
              <Translate contentKey="archiscapeCoreApp.contentStoreInternal.version">Version</Translate>
            </span>
          </dt>
          <dd>{contentStoreInternalEntity.version}</dd>
          <dt>
            <span id="insertDate">
              <Translate contentKey="archiscapeCoreApp.contentStoreInternal.insertDate">Insert Date</Translate>
            </span>
          </dt>
          <dd>
            {contentStoreInternalEntity.insertDate ? (
              <TextFormat value={contentStoreInternalEntity.insertDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="fileName">
              <Translate contentKey="archiscapeCoreApp.contentStoreInternal.fileName">File Name</Translate>
            </span>
          </dt>
          <dd>{contentStoreInternalEntity.fileName}</dd>
          <dt>
            <span id="content">
              <Translate contentKey="archiscapeCoreApp.contentStoreInternal.content">Content</Translate>
            </span>
          </dt>
          <dd>{contentStoreInternalEntity.content}</dd>
        </dl>
        <Button tag={Link} to="/content-store-internal" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/content-store-internal/${contentStoreInternalEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ContentStoreInternalDetail;
